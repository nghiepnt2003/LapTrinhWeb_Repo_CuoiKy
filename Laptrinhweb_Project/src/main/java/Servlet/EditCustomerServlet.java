package Servlet;

import DBUtil.DBUtil;
import Entity.Account;
import Entity.Customer;
import Entity.Product;
import EntityDB.AccountDB;
import EntityDB.CustomerDB;
import EntityDB.ProductDB;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="editcustomer",value="/editcustomer")
public class EditCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Long id = Long.parseLong(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String mail = req.getParameter("mail");
        String deliveryAddress = req.getParameter("deliveryAddress");
        Customer customerToUpdate = em.find(Customer.class,id);// cập nhật sản phẩm theo id
        // Tạo một đối tượng Customer để cập nhật Customer
        Customer customer = CustomerDB.getCustomerByID(id);
        // Tạo một đối tượng Account để cập nhật Account của Customer
        Account accountToUpdate = em.find(Account.class,customer.getAccount().getId());

        //  setdata
        accountToUpdate.setUserName(username);
        accountToUpdate.setPassword(password);

        customerToUpdate.setName(name);
        customerToUpdate.setPhone(phone);
        customerToUpdate.setMail(mail);
        customerToUpdate.setDeliveryAddress(deliveryAddress);
        customerToUpdate.setAccount(accountToUpdate);

        //Cập nhật
        // update customer thì tự động update account vì có lan truyền
        CustomerDB.update(customerToUpdate);
        resp.sendRedirect("managecustomer");

    }
}
