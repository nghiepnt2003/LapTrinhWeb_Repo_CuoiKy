package Servlet;

import Entity.Cart;
import Entity.CartLine;
import EntityDB.CartDB;
import EntityDB.CartLineDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="quantitycartline",value="/quantitycartline")
public class QuantityCartLineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Long cartlineID = Long.parseLong(req.getParameter("cartlineID"));
        String action = req.getParameter("action");
        CartLine cartLine = CartLineDB.getCartLineByID(cartlineID);
        if(action.equals("add"))
        {
            cartLine.setQuantity(cartLine.getQuantity() + 1);
        }else
        if(action.equals("minus")){
            if(cartLine.getQuantity() > 1)
            {
                cartLine.setQuantity(cartLine.getQuantity() - 1);
            }
        }
        CartLineDB.update(cartLine);
        // Chuyển hướng ngược về url cũ
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }
}
