package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveItemFromCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";
    private String workingItemId;
    private CartService cartService;
    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");
        cartService = new CartService();

        HttpSession session = request.getSession();
        cart = (Cart)session.getAttribute("cart");
        Account account = (Account)session.getAttribute("account");



        if(cart.getCartItems() == null){
            session.setAttribute("message","你试图从一个空的购物车删除物品");
            request.getRequestDispatcher(ERROR).forward(request,response);
        }else{
            CartItem cartItem = cart.getCartItemById(workingItemId);
            cartService.deleteCartByItemId(cartItem,account.getUsername());
            Item item = cart.removeItemById(workingItemId);
            request.getRequestDispatcher(VIEW_CART).forward(request,response);
        }

    }
}
