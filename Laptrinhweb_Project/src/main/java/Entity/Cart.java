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
    private List<CartLine> cartLines;


    public Cart() {
    }

    public Cart(int id) {
        this.id = id;
        this.cartLines = new ArrayList<CartLine>();
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
