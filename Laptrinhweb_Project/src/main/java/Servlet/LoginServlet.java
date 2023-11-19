package Servlet;

import Entity.Account;
import EntityDB.AccountDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="login",value="/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        Account account = AccountDB.getAccount(user,pass);

        if(account != null) {
            // Tạo session
            HttpSession session = req.getSession();
            // Đẩy account lên session
            session.setAttribute("acc",account);

            resp.sendRedirect("home");
//            req.getRequestDispatcher("Home.jsp").forward(req, resp);
        }else {
            req.setAttribute("mess","Account doesn't exist !!!!");
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }
    }

}
