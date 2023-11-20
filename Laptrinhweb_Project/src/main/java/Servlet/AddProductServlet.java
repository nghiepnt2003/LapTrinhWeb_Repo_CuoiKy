package Servlet;

import Entity.Category;
import Entity.Product;
import EntityDB.CategoryDB;
import EntityDB.ProductDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="add",value="/add")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String brand = req.getParameter("brand");
        String image = req.getParameter("image");
        String color = req.getParameter("color");
        int size = Integer.parseInt(req.getParameter("size"));
        Double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        // Lấy category từ CateID
        Category category = CategoryDB.getCategoryByID(Integer.parseInt(req.getParameter("category")));


        Product product = new Product(name,brand,image,color,size,price,description,category);
        ProductDB.insert(product);
        resp.sendRedirect("manageproduct");
    }
}
