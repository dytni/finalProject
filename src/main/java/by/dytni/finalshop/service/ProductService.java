package by.dytni.finalshop.service;

import by.dytni.finalshop.domain.product.Product;
import by.dytni.finalshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts(String name) {
        if (name == null) {
            return productRepository.findAll();
        } else {
            return productRepository.findByName(name);
        }
    }

    public Product getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    List<Product> getAllProductsOfType(String type) {
        return productRepository.findByType(type);
    }

    public void saveProduct(Product product) {
        log.info("Saving new {}", product);
        productRepository.save(product);
    }

    public void deleteProductById(Integer id) {
        log.info("Delete product {}", id);
        productRepository.deleteById(id);
    }


}
