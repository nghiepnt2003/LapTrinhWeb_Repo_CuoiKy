package Servlet;

import Entity.Account;
import EntityDB.AccountDB;
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
        if(AccountDB.getAccount(user,pass) != null){
            req.setAttribute("mess","Account already exists !!!!");
        }else
        if(pass.equals(repass)){
            Account account = new Account(user,pass,true);
            AccountDB accountDB = new AccountDB();
            accountDB.insert(account);
            req.setAttribute("mess_success","Account registration successful");
        }
        req.getRequestDispatcher("Login.jsp").forward(req,resp);
    }
}
