package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShippingFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("order");
        if(order == null){
            order = new Order();
        }
        order.setBillToFirstName(request.getParameter("billToFirstName"));
        order.setBillToLastName(request.getParameter("billToLastName"));
        order.setBillAddress1(request.getParameter("billAddress1"));
        order.setBillAddress2(request.getParameter("billAddress2"));
        order.setBillCity(request.getParameter("billCity"));
        order.setBillState(request.getParameter("billState"));
        order.setBillZip(request.getParameter("billZip"));
        order.setBillCountry(request.getParameter("billCountry"));

        session.setAttribute("billToFirstName",request.getParameter("billToFirstName"));
        session.setAttribute("billToLastName",request.getParameter("billToLastName"));
        session.setAttribute("billAddress1",request.getParameter("billAddress1"));
        session.setAttribute("billAddress2",request.getParameter("billAddress2"));
        session.setAttribute("billCity",request.getParameter("billCity"));
        session.setAttribute("billState",request.getParameter("billState"));
        session.setAttribute("billZip",request.getParameter("billZip"));
        session.setAttribute("billCountry",request.getParameter("billCountry"));

        //默认相同
        order.setShipToFirstName(request.getParameter("billToFirstName"));
        order.setShipToLastName(request.getParameter("billToLastName"));
        order.setShipAddress1(request.getParameter("billAddress1"));
        order.setShipAddress2(request.getParameter("billAddress2"));
        order.setShipCity(request.getParameter("billCity"));
        order.setShipState(request.getParameter("billState"));
        order.setShipZip(request.getParameter("billZip"));
        order.setShipCountry(request.getParameter("billCountry"));

        session.setAttribute("order",order);
    }
}
