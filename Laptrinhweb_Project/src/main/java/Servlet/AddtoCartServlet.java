package Servlet;

import DBUtil.DBUtil;
import Entity.Cart;
import Entity.CartLine;
import Entity.Customer;
import Entity.Product;
import EntityDB.CartDB;
import EntityDB.CartLineDB;
import EntityDB.CustomerDB;
import EntityDB.ProductDB;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="addtocart",value="/addtocart")
public class AddtoCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        Long customerID = Long.parseLong(req.getParameter("customerid"));
        Long productID = Long.parseLong(req.getParameter("productID"));
        Customer customer = CustomerDB.getCustomerByID(customerID);
        Product product = ProductDB.getProductByID(productID);

        // Tạo ra cartline từ sản phẩm đã chọn
        Long quantity = Long.parseLong("1");

        Long cartID = CartDB.getCartByCustomer(customer).getId();
        //cập nhật sản phẩm theo id
        // tìm Cart theo cart ID
        Cart cart = em.find(Cart.class,cartID);

        if(cart.getCartLines() == null){
            CartLine cartLine = new CartLine(product,quantity);
            // phải tạo một list cartline = rỗng trước, vì khi không tạo thì nó hiểu cartlines = null không add  được
            cart.setCartLines(new ArrayList<CartLine>());
            cart.getCartLines().add(cartLine);
        }else
            // Kiểm tra trong cartline của cart đã select có chứa product đó hay chưa
        if (cart.containsProduct(product))
        {
            // Nếu có rồi thì lấy ra cartline đó
            CartLine cartLine = cart.getCartLineContainsProduct(product);
            // Cộng số lượng lên 1
            cartLine.setQuantity(cartLine.getQuantity()+1);
        }
        else // Nếu cartlinelist của cart đã select không chứa product đó
        {
            // Tạo ra một cartline mới từ product đó
            CartLine cartLine = new CartLine(product,quantity);
            // thêm cartline vào cartlineList
            // Vì đã kiểm tra null ở trên nên không cần sợ cartlineList == null nữa
            cart.getCartLines().add(cartLine);
        }
        // sau khi hoàn thành thì update cart
        CartDB.update(cart);
        Long cartlinesCount = cart.getCartLines().stream().count();
        req.setAttribute("customerid",customerID);
        req .setAttribute("cartlinecount",cartlinesCount);
        req.setAttribute("list",cart.getCartLines());
        req.getRequestDispatcher("showcart").forward(req,resp);
    }

}
