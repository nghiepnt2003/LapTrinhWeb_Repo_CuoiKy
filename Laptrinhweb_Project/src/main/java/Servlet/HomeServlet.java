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
@WebServlet(name="home",value="/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ProductDB productDB = new ProductDB();
        List<Product> productList = productDB.getAllProduct();
        Product productLast = productDB.getLastProduct();



        req.setAttribute("listP",productList);


        // Chuyển dữ liệu đến trang Home.jsp
        req.getRequestDispatcher("Home.jsp").forward(req,resp);
    }
}
