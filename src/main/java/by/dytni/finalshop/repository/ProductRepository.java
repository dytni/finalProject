package by.dytni.finalshop.repository;


import by.dytni.finalshop.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByName(String name);

    List<Product> findByType(String type);
}
