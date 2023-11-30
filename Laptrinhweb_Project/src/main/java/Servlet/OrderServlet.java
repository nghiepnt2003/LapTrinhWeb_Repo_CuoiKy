package Servlet;

import Entity.Cart;
import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;
import EntityDB.OrderDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="order",value="/order")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("customer") == null) {
            resp.sendRedirect("Login.jsp");
            return;
        }
        String action = req.getParameter("action");
        if ("add".equals(action)){
            addOrder(req);
        }
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }

    private void addOrder(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        Customer customer = (Customer)session.getAttribute("customer");

        List<OrderDetail> listOrderDetails = new ArrayList<OrderDetail>();
        for (var item:cart.getCartLines()) {
            String productName = item.getProduct().getProductName();
            String brand = item.getProduct().getBrand();
            String productImage = item.getProduct().getProductImage();
            String color = item.getProduct().getColor();
            int size = item.getProduct().getSize();
            Double productCost = item.getProduct().getProductCost();
            String description = item.getProduct().getDescription();
            Long quantity = item.getQuantity();
            OrderDetail orderDetail = new OrderDetail(productName,brand,productImage,color,size,productCost,description,quantity);
            listOrderDetails.add(orderDetail);
        }
        Double totalPrice = Double.parseDouble(req.getParameter("totalPrice"));
        Order order = new Order(customer,totalPrice,listOrderDetails);
        OrderDB.insert(order);
    }
}
