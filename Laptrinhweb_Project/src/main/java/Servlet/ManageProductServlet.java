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
import java.util.List;

@WebServlet(name="manageproduct",value="/manageproduct")
public class ManageProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        CategoryDB categoryDB = new CategoryDB();

        List<Product> productList = ProductDB.getAllProduct();
        List<Category> categoryList = categoryDB.getAllCategory();

        req.setAttribute("listCC",categoryList);

        req.setAttribute("listP",productList);

        req.getRequestDispatcher("ManagerProduct.jsp").forward(req,resp);

    }
}
