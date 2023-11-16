package Entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    // Siêng năng : Truy vấn 1  đối tượng là truy vấn quan hệ ví dụ như trên sẽ truy vấn cartline
    // Lan truyền
    private List<CartLine> cartLines;
    @OneToOne
    private Customer customer;

    public Cart() {
    }

    public Cart(Customer customer) {
        this.customer = customer;
        this.cartLines = new ArrayList<CartLine>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "accountID=" + id +
                ", CartLines=" + cartLines +
                '}';
    }


}
