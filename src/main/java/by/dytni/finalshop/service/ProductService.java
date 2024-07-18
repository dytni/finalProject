package by.dytni.finalshop.service;

import by.dytni.finalshop.domain.Image;
import by.dytni.finalshop.domain.Product;
import by.dytni.finalshop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
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
    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException, SQLException {
        Image image1;
        Image image2;
        Image image3;

        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImage(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImage(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImage(image3);
        }

        log.info("Saving new {}", product);
        Product productDB = productRepository.save(product);
        productDB.setPreviewImageId(productDB.getImageList().get(0).getId());
        productRepository.save(productDB);
    }

    private Image toImageEntity(MultipartFile file) throws SQLException, IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        Blob blob = new javax.sql.rowset.serial.SerialBlob(file.getBytes());
        image.setBlob(blob);
        return image;
    }

    public void deleteProductById(Integer id) {
        log.info("Delete product {}", id);
        productRepository.deleteById(id);
    }


}
