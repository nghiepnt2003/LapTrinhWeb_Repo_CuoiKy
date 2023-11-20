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

@WebServlet(name="loadProduct",value="/loadProduct")
public class loadProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        CategoryDB categoryDB = new CategoryDB();

        List<Category> categoryList = categoryDB.getAllCategory();
        int pid = Integer.parseInt(req.getParameter("pid"));
        Product product = ProductDB.getProductByID(pid);

        req.setAttribute("detail",product);
        req.setAttribute("listCC",categoryList);
        req.getRequestDispatcher("Edit.jsp").forward(req,resp);
    }

}
