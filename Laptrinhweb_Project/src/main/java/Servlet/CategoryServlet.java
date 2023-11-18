package Servlet;

import Entity.Product;
import EntityDB.ProductDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="category",value="/category")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String CateID = req.getParameter("cid");
        ProductDB productDB = new ProductDB();
        List<Product> productListbyCID = productDB.getProductsByCID(CateID);

        req.setAttribute("listP",productListbyCID);
        req.getRequestDispatcher("Home.jsp").forward(req,resp);
    }
}
