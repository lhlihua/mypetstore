package org.csu.mypetstore.web.servlet.view;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewNewOrderFormServlet extends HttpServlet {
    private static final String NEW_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        Account account = (Account)session.getAttribute("account");

        session.setAttribute("cart",cart);
        session.setAttribute("creditCard","999 9999 9999 9999");
        session.setAttribute("expiryDate","12/03");
        session.setAttribute("billToFirstName",account.getFirstName());
        session.setAttribute("billToLastName",account.getLastName());
        session.setAttribute("billAddress1",account.getAddress1());
        session.setAttribute("billAddress2",account.getAddress2());
        session.setAttribute("billCity",account.getCity());
        session.setAttribute("billState",account.getState());
        session.setAttribute("billZip",account.getZip());
        session.setAttribute("billCountry",account.getCountry());
        request.getRequestDispatcher(NEW_ORDER).forward(request,response);
    }
}
