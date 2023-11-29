package Servlet;

import DBUtil.DBUtil;
import Entity.Cart;
import Entity.CartLine;
import Entity.Customer;
import Entity.Product;
import EntityDB.CartDB;
import EntityDB.CartLineDB;
import EntityDB.CustomerDB;
import EntityDB.ProductDB;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "cart", value = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
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
        Cart cart = (Cart)session.getAttribute("cart");
        if("showcart".equals(action))
        {
            showcart(req,cart);
            req.getRequestDispatcher("Cart.jsp").forward(req,resp);

        }else if ("add".equals(action)) {
            addToCart(req, cart);
        } else if ("delete".equals(action)) {
            deletefromcart(req, cart);
        }else if ("increateQuantity".equals(action)){
            increaseQuantity(req,cart);
        }else if ("decreateQuantity".equals(action)){
            decreaseQuantity(req,cart);;
        }
        CartDB.update(cart);
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }

    private void addToCart(HttpServletRequest req, Cart cart) {
        Long productID = Long.parseLong(req.getParameter("productID"));
        Product product = ProductDB.getProductByID(productID);
        Long quantity = Long.parseLong("1");

        if (cart.getCartLines() == null) {
            CartLine cartLine = new CartLine(product, quantity);
            // phải tạo một list cartline = rỗng trước, vì khi không tạo thì nó hiểu cartlines = null không add  được
            cart.setCartLines(new ArrayList<CartLine>());
            cart.getCartLines().add(cartLine);
        } else
            // Kiểm tra trong cartline của cart đã select có chứa product đó hay chưa
            if (cart.containsProduct(product)) {
                // Nếu có rồi thì lấy ra cartline đó
                CartLine cartLine = cart.getCartLineContainsProduct(product);
                cartLine.setQuantity(cartLine.getQuantity() + 1);
            } else // Nếu cartlinelist của cart đã select không chứa product đó
            {
                // Tạo ra một cartline mới từ product đó
                CartLine cartLine = new CartLine(product, quantity);
                // thêm cartline vào cartlineList
                // Vì đã kiểm tra null ở trên nên  cartlineList != null
                cart.getCartLines().add(cartLine);
            }
        // sau khi hoàn thành thì update cart
    }

    private void deletefromcart(HttpServletRequest req, Cart cart) {

        Long cartLineID = Long.parseLong(req.getParameter("cartlineID"));
        CartLine cartLine = CartLineDB.getCartLineByID(cartLineID);
        cart.removeCartLine(cartLine);
        CartDB.update(cart);
        CartLineDB.delete(cartLine);
    }
    private void showcart(HttpServletRequest req, Cart cart)
    {
        Long cartlinesCount = cart.getCartLines().stream().count();
        req .setAttribute("cartlinecount",cartlinesCount);
        req.setAttribute("list",cart.getCartLines());
    }

    private void increaseQuantity(HttpServletRequest req,Cart cart) {

        Long cartLineID = Long.parseLong(req.getParameter("cartlineID"));
        CartLine cartLine = CartLineDB.getCartLineByID(cartLineID);
        cartLine.setQuantity(cartLine.getQuantity() + 1);
        CartLineDB.update(cartLine);
    }
    private void decreaseQuantity(HttpServletRequest req,Cart cart) {

        Long cartLineID = Long.parseLong(req.getParameter("cartlineID"));
        CartLine cartLine = CartLineDB.getCartLineByID(cartLineID);
        if(cartLine.getQuantity() > 1)
        {
            cartLine.setQuantity(cartLine.getQuantity() - 1);
        }

    }
}
