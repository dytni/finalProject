package by.dytni.finalshop.domain.product;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "product")
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name", unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    ProductType type;
    @Column(name = "coast")
    Float coast;
}
