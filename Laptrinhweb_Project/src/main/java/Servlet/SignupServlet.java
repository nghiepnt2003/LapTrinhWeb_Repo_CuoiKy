package Servlet;

import Entity.Account;
import Entity.Cart;
import Entity.Customer;
import EntityDB.AccountDB;
import EntityDB.CartDB;
import EntityDB.CustomerDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="signup",value = "/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String deliveryAddress = req.getParameter("deliveryAddress");
        if(AccountDB.getAccount(user,pass) != null){
            req.setAttribute("mess","Account already exists !!!!");
        }else
        if(pass.equals(repass)){
            Account account = new Account(user,pass,true);
            Customer customer = new Customer(name,phone,email,deliveryAddress,account);
            CustomerDB.insert(customer);
            Cart cart = new Cart(customer);
            CartDB.insert(cart);
            req.setAttribute("mess_success","Account registration successful");
        }
        req.getRequestDispatcher("Login.jsp").forward(req,resp);
    }
}
