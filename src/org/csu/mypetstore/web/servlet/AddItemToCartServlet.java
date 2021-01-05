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

public class AddItemToCartServlet extends HttpServlet {

    //处理完请求跳转界面
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    //定义处理该请求需要的数据
    private String workingItemId;
    private Cart cart;
    //定义处理该请求需要的业务逻辑层
    private CatalogService catalogService;
    private CartService cartService;
    public AddItemToCartServlet(){
        catalogService = new CatalogService();
        cartService = new CartService();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId = req.getParameter("workingItemId");
        HttpSession session = req.getSession();
        Account account = (Account)session.getAttribute("account");
        Boolean accountLogin = (Boolean) session.getAttribute("accountLogin");
        if(!accountLogin){
            req.getRequestDispatcher("viewLoginForm").forward(req,resp);
        }
        cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
            CartItem cartItem = cart.getCartItemById(workingItemId);
            cartService.updateCartByItemId(cartItem,account.getUsername());
        } else {
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
            CartItem cartItem = cart.getCartItemById(workingItemId);
            cartService.insertCart(cartItem,account.getUsername());
        }
        session.setAttribute("cart",cart);
        req.getRequestDispatcher("viewCart").forward(req,resp);
    }
}
