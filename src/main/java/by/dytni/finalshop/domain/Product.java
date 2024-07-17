package by.dytni.finalshop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "coast")
    private Float coast;
    @Column(name = "size")
    private String size;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Image> imageList = new ArrayList<>();
    @Column(name = "preview_image_id")
    private Integer previewImageId;

    public void addImage(Image image) {
        image.setProduct(this);
        imageList.add(image);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", coast=" + coast +
                ", size='" + size + '\'' +
                ", previewImageId=" + previewImageId +
                '}';
    }
}

