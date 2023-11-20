package Servlet;

import DBUtil.DBUtil;
import Entity.Category;
import Entity.Product;
import EntityDB.CategoryDB;
import EntityDB.ProductDB;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="edit",value="/edit")
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String brand = req.getParameter("brand");
        String image = req.getParameter("image");
        String color = req.getParameter("color");
        int size = Integer.parseInt(req.getParameter("size"));
        Double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");

        // Lấy category từ CateID
        Category category = CategoryDB.getCategoryByID(Integer.parseInt(req.getParameter("category")));
        // Tạo một đối tượng Product để cập nhật
        Product productToUpdate = em.find(Product.class,id);// cập nhật sản phẩm theo id

        //  setdata
        productToUpdate.setProductName(name);
        productToUpdate.setBrand(brand);
        productToUpdate.setProductImage(image);
        productToUpdate.setColor(color);
        productToUpdate.setSize(size);
        productToUpdate.setProductCost(price);
        productToUpdate.setDescription(description);

        //Cập nhật
        ProductDB.update(productToUpdate);


        resp.sendRedirect("manageproduct");
    }

}
