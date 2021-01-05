package org.csu.mypetstore.web.servlet.view;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewEditAccountFormServlet extends HttpServlet {
    private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";
    private OrderService orderService;
    private List<Order> orderList;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");
        orderList = new ArrayList<>();
        orderService = new OrderService();
        orderList = orderService.getOrdersByUsername(account.getUsername());
        session.setAttribute("orderList",orderList);
        session.setAttribute("username",account.getUsername());
        session.setAttribute("firstName",account.getFirstName());
        session.setAttribute("lastName",account.getLastName());
        session.setAttribute("email",account.getEmail());
        session.setAttribute("phone",account.getPhone());
        session.setAttribute("address1",account.getAddress1());
        session.setAttribute("address2",account.getAddress2());
        session.setAttribute("city",account.getCity());
        session.setAttribute("state",account.getState());
        session.setAttribute("zip",account.getZip());
        session.setAttribute("country",account.getCountry());
        session.setAttribute("mylanguage",account.getLanguagePreference());
        session.setAttribute("myfavouriteCategoryId",account.getFavouriteCategoryId());
        request.getRequestDispatcher(EDIT_ACCOUNT).forward(request,response);
    }
}
