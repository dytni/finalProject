package by.dytni.finalshop.domain;

import by.dytni.finalshop.domain.Product;
import by.dytni.finalshop.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity(name = "cart")
@Table(name = "carts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(mappedBy = "cart")
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Float calculateTotalCost() {
        Float totalCost = 0.0F;
        if (products != null) {
            for (Product product : products) {
                totalCost += product.getCoast();
            }
        }
        return totalCost;
    }
}
