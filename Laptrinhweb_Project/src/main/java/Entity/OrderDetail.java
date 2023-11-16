package Entity;

import jakarta.persistence.*;

@Entity
@Table(name="OrderDetail")
public class OrderDetail {
    @Id
    private Long id;
    @ManyToOne(fetch=FetchType.EAGER)
    private Product product;
    private Long quantity;


    public OrderDetail() {
    }

    public OrderDetail(Product product, Long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
