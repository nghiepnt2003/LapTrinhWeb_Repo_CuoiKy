package Servlet;

import Entity.Cart;
import Entity.Customer;
import EntityDB.CartDB;
import EntityDB.CustomerDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="deleteCustomer",value="/deleteCustomer")
public class DeleteCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Long cusid = Long.parseLong(req.getParameter("cusid"));
        System.out.println(cusid);

        Customer customer = CustomerDB.getCustomerByID(cusid);

        // Do Mỗi khi tạo ra một khách hàng thì sẽ tạo ra một cart cho khách hàng đó
        Cart cart = CartDB.getCartByCustomer(customer);
        // Nên phải xóa cart trước khi xóa khách hàng để đảm bảo không bị lỗi
        CartDB.delete(cart);
        CustomerDB.delete(customer);
        resp.sendRedirect("managecustomer");
    }
}
