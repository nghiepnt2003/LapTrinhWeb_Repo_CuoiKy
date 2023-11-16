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
    @ManyToOne()
    private Customer customer;

    private int totalQuantity;
    private Date orderDate;
    private Date deliveryData;
    private String deliveryStatus;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;


    public Order() {
    }

    public Order(Customer customer, int totalQuantity, Date orderDate, Date deliveryData, String deliveryStatus) {
        this.customer = customer;
        this.totalQuantity = totalQuantity;
        this.orderDate = orderDate;
        this.deliveryData = deliveryData;
        this.deliveryStatus = deliveryStatus;
        this.orderDetails = new ArrayList<OrderDetail>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryData() {
        return deliveryData;
    }

    public void setDeliveryData(Date deliveryData) {
        this.deliveryData = deliveryData;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
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
}
