package by.dytni.finalshop.service;

import by.dytni.finalshop.domain.Image;
import by.dytni.finalshop.domain.Product;
import by.dytni.finalshop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts(String name) {
        if (name == null || name.isEmpty()) {
            return productRepository.findAll();
        } else {
            return productRepository.findByName(name);
        }
    }

    public Product getProductById(Integer id) {
        log.info("Getting product by ID: {}", id);
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Transactional
    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;

        if (file1.getSize() != 0) {
            image1 = toCompressedImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImage(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toCompressedImageEntity(file2);
            product.addImage(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toCompressedImageEntity(file3);
            product.addImage(image3);
        }

        log.info("Saving new {}", product);
        Product productDB = productRepository.save(product);
        productDB.setPreviewImageId(productDB.getImageList().get(0).getId());
        productRepository.save(productDB);
    }

    private Image toCompressedImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());

        // Сжатие изображения
        byte[] compressedBytes = compressImage(file.getBytes());
        image.setBytes(compressedBytes);

        return image;
    }

    private byte[] compressImage(byte[] originalBytes) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(originalBytes);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Сжатие изображения до 800x600 пикселей, можно изменить размеры по необходимости
        Thumbnails.of(inputStream)
                .size(800, 600)
                .outputFormat("jpg")
                .toOutputStream(outputStream);

        return outputStream.toByteArray();
    }

    public void deleteProductById(Integer id) {
        log.info("Delete product {}", id);
        productRepository.deleteById(id);
    }


}
