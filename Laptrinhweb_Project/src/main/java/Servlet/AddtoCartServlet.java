package Servlet;

import DBUtil.DBUtil;
import Entity.Cart;
import Entity.CartLine;
import Entity.Customer;
import Entity.Product;
import EntityDB.CartDB;
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
        CartLine cartLine = new CartLine(product,quantity);
        // Lấy ra cart từ customer và thêm cartline vào cart
        Long cartID = CartDB.getCartByCustomer(customer).getId();
        //cập nhật sản phẩm theo id
        // tìm Cart theo cart ID
        Cart cart = em.find(Cart.class,cartID);
        if(cart.getCartLines() == null)
        {
            cart.setCartLines(new ArrayList<CartLine>());
            cart.getCartLines().add(cartLine);
        }
        else if (cart.containsProduct(product))
        {
            cartLine.setQuantity(cartLine.getQuantity()+1);
        }else{
            cart.getCartLines().add(cartLine);
            System.out.println(2);
        }
        CartDB.update(cart);

        req.setAttribute("list",cart.getCartLines());
        req.getRequestDispatcher("Cart.jsp").forward(req,resp);
    }

}
