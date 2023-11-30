package Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"Order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    private Customer customer;

    private Double totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;


    public Order() {
    }

    public Order(Customer customer, Double totalPrice,List<OrderDetail> orderDetails) {
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.orderDetails = new ArrayList<OrderDetail>();
        setDetails(orderDetails);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private void setDetails(List<OrderDetail> orderDetails) {
        for (var item :orderDetails) {
            String productName = item.getProductName();
            String brand = item.getBrand();
            String productImage = item.getProductImage();
            String color = item.getColor();
            int size = item.getSize();
            Double productCost = item.getProductCost();
            String description = item.getDescription();
            Long quantity = item.getQuantity();
            this.orderDetails.add(new OrderDetail(productName, brand,productImage,color,size,productCost,description,quantity));
        }
    }

}
