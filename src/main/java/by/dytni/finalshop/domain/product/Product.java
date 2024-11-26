package by.dytni.finalshop.domain.product;

import jakarta.persistence.*;
import lombok.*;

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
    @Setter
    @Getter
    @Column(name = "coast")
    Float coast;
    @Column(name = "size")
    String size;

}
