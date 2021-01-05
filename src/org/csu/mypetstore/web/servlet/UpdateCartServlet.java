package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class UpdateCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        Account account = (Account)session.getAttribute("account");
        CartService cartService = new CartService();
        //循环遍历购物车
        for(Iterator itr = cart.getCartItems(); itr.hasNext();){
            CartItem cartItem = (CartItem)itr.next();
            cartItem.setQuantity(Integer.parseInt(request.getParameter(cartItem.getItem().getItemId())) );
            cartItem.setInStock(Integer.parseInt(request.getParameter(cartItem.getItem().getItemId())) > 0);
            cartService.updateCartByItemId(cartItem,account.getUsername());
        }
        request.getRequestDispatcher("viewCart").forward(request,response);
    }
}
