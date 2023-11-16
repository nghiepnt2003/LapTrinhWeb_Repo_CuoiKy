package Servlet;

import Entity.Account;
import EntityDB.AccountDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="login",value="/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        Account account = AccountDB.getAccount(user,pass);
        if(account != null) {
            req.getRequestDispatcher("Home.jsp").forward(req, resp);
        }else {
            req.setAttribute("mess","Account doesn't exist !!!!");
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }
    }

}
