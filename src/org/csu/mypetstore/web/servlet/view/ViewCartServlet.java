package org.csu.mypetstore.web.servlet.view;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Boolean accountLogin = (Boolean) session.getAttribute("accountLogin");
        if(!accountLogin){
            req.getRequestDispatcher("viewLoginForm").forward(req,resp);
        }

        Cart cart = (Cart)session.getAttribute("cart");
        Account account = (Account)session.getAttribute("account");
        CartService cartService = new CartService();
        if(cart == null){
            cart = new Cart();
        }
        if(cartService.getCartByUsername(account.getUsername()) != null)
            cart.setCartItemList(cartService.getCartByUsername(account.getUsername()));
        session.setAttribute("cart",cart);
        req.getRequestDispatcher(VIEW_CART).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
