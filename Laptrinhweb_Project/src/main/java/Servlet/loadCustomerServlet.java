package Servlet;

import Entity.Customer;
import EntityDB.CustomerDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="loadCustomer",value="/loadCustomer")
public class loadCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Long cusid = Long.parseLong(req.getParameter("cusid"));
        Customer customer = CustomerDB.getCustomerByID(cusid);
        req.setAttribute("detail",customer);

        req.getRequestDispatcher("EditCustomer.jsp").forward(req,resp);

    }
}
