package Servlet;

import DBUtil.DBUtil;
import Entity.Cart;
import Entity.Customer;
import EntityDB.CartDB;
import EntityDB.CustomerDB;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "showcart", value = "/showcart")
public class ShowCartServlet extends HttpServlet {
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
        Customer customer = CustomerDB.getCustomerByID(customerID);
        // Lấy ra cart từ customer và thêm cartline vào cart
        Long cartID = CartDB.getCartByCustomer(customer).getId();
        // tìm Cart theo cart ID
        Cart cart = em.find(Cart.class,cartID);
        //Số lượng cartline
        Long cartlinesCount = cart.getCartLines().stream().count();
        req.setAttribute("customerid",customerID);
        req.setAttribute("cartlineid",cartID);
        req .setAttribute("cartlinecount",cartlinesCount);
        req.setAttribute("list",cart.getCartLines());
        req.getRequestDispatcher("Cart.jsp").forward(req,resp);
    }
}
