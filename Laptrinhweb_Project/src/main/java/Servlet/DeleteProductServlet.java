package Servlet;

import Entity.Cart;
import Entity.CartLine;
import Entity.Product;
import EntityDB.CartDB;
import EntityDB.CartLineDB;
import EntityDB.ProductDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="deleteproduct",value="/deleteproduct")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Long pid = Long.parseLong(req.getParameter("pid"));
        Product product = ProductDB.getProductByID(pid);
        List<CartLine> cartLines = CartLineDB.getCartLinesByProductID(pid);
        for (var item: cartLines) {
            Cart cart = CartDB.getCartByCartLine(item);
            cart.removeCartLine(pid);
            CartDB.update(cart);
        }
        ProductDB.delete(product);
        resp.sendRedirect("manageproduct");
    }
}
