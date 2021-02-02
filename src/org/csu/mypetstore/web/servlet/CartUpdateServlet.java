package org.csu.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.util.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物车中该物品的数量
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String cartItemid = request.getParameter("cartItemid");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        CartService cartService = new CartService();
        cartService.updateCartByItemId(cartItemid,username,quantity);
    }
}
