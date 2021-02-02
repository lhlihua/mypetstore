package org.csu.mypetstore.web.servlet.view;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ViewConfirmOrderServlet extends HttpServlet {
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String SHIPPING = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private Order order;
    private Order orderstatus;
    private OrderService orderService;
    private CatalogService catalogService;
    private CartService cartService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        orderService = new OrderService();
        catalogService = new CatalogService();
        int orderId = orderService.getNextId("ordernum");
        BigDecimal bigDecimal = new BigDecimal("0");
        Account account = (Account)session.getAttribute("account");
        Cart cart = (Cart)session.getAttribute("cart");

        for(Iterator itr = cart.getCartItems();itr.hasNext();){
            LineItem lineItem = new LineItem();
            CartItem cartItem = (CartItem)itr.next();
            bigDecimal = bigDecimal.add(cartItem.getTotal());
            lineItem.setItemId(cartItem.getItem().getItemId());
            lineItem.setItem(catalogService.getItem(lineItem.getItemId()));
            lineItem.setQuantity(cartItem.getQuantity());
            lineItem.setUnitPrice(cartItem.getTotal());
            lineItem.setOrderId(orderId);
            lineItem.setLineNumber(orderService.getNextId("linenum"));
            orderService.insertLineItem(lineItem);
        }
        Date date = new Date();
        order = (Order) session.getAttribute("order");

        order.setStatus("P");
        order.setLineItems(orderService.getLineItemsByOrderId(orderId));
        order.setOrderId(orderId);
        order.setOrderDate(date);
        order.setUsername(account.getUsername());
        order.setCourier("Faster");
        order.setTotalPrice(bigDecimal);
        order.setExpiryDate("1-1");
        //标记
        order.setLocale(account.getCountry());
        order.setCreditCard(request.getParameter("creditCard"));
        order.setCardType(request.getParameter("cardType"));

        order.setShipToFirstName(request.getParameter("billToFirstName"));

        System.out.println(request.getParameter("billToFirstName"));

        order.setShipToFirstName(request.getParameter("shipToFirstName"));
        order.setShipAddress1(request.getParameter("shipAddress1"));
        order.setShipAddress2(request.getParameter("shipAddress2"));
        order.setShipCity(request.getParameter("shipCity"));
        order.setShipState(request.getParameter("shipState"));
        order.setShipZip(request.getParameter("shipZip"));
        order.setShipCountry(request.getParameter("shipCountry"));

        session.setAttribute("order",order);
        cartService = new CartService();
        List<CartItem>cartItems = cart.getCartItemList();
        int len = cartItems.size();
        for(int i = 0 ; i < len ; i++){
            cartService.deleteCartByItemId(cartItems.get(0),account.getUsername());
            cart.removeItemById(cartItems.get(0).getItem().getItemId());
        }
        request.getRequestDispatcher(CONFIRM_ORDER).forward(request,response);
    }
}
