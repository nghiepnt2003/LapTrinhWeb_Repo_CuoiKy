package Servlet;

import Entity.Customer;
import EntityDB.CustomerDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="loadProfile",value="/loadProfile")
public class loadProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        loadProfile(req);
        req.getRequestDispatcher("Profile.jsp").forward(req,resp);

    }

    private void loadProfile(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Customer customer = (Customer)session.getAttribute("customer");
        req.setAttribute("detail",customer);
    }
}
