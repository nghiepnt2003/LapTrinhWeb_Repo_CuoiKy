package Servlet;

import DBUtil.DBUtil;
import Entity.Cart;
import Entity.CartLine;
import Entity.Customer;
import EntityDB.CartDB;
import EntityDB.CartLineDB;
import EntityDB.CustomerDB;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="deletecartline",value="/deletecartline")
public class DeleteCartLineServlet extends HttpServlet {
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
        Long cartLineID = Long.parseLong(req.getParameter("cartlineid"));


        Customer customer = CustomerDB.getCustomerByID(customerID);
        Long cartID = CartDB.getCartByCustomer(customer).getId();
        Cart cart = em.find(Cart.class,cartID);

        CartLine cartLine = CartLineDB.getCartLineByID(cartLineID);
        cart.removeCartLine(cartLine);
        CartDB.update(cart);
        CartLineDB.delete(cartLine);
        req.setAttribute("customerid",customerID);
        // Chuyển hướng ngược về url cũ
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }
}
