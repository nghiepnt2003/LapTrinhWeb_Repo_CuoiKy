package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ShippingInfo")
public class ShippingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double shippingCost;
    private String shippingType;
    private String shippingAddress;


    public ShippingInfo() {
    }

    public ShippingInfo(double shippingCost, String shippingType, String shippingAddress) {
        this.shippingCost = shippingCost;
        this.shippingType = shippingType;
        this.shippingAddress = shippingAddress;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
