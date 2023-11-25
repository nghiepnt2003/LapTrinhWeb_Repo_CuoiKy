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

@WebServlet(name="detail",value="/detail")
public class DetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Long pid = Long.parseLong(req.getParameter("pid"));
        ProductDB productDB = new ProductDB();
        CategoryDB categoryDB = new CategoryDB();
        List<Category> categoryList = categoryDB.getAllCategory();
        Product productLast = productDB.getLastProduct();
        Product product = productDB.getProductByID(pid);

        req.setAttribute("detail",product);
        req.setAttribute("listCC",categoryList);
        req.setAttribute("productLast",productLast);

        req.getRequestDispatcher("Detail.jsp").forward(req,resp);
    }
}
