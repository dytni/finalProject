package by.dytni.finalshop.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "product")
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "type")
    String type;
    @Column(name = "coast")
    Float coast;
    @Column(name = "size")
    String size;

}
