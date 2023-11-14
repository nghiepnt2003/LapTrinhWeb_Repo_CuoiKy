package Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int customerID;
    private int productID;
    private int totalQuantity;
    private Date orderData;
    private Date deliveryData;
    private String deliveryStatus;
    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;


    public Order() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
