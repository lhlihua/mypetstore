package org.csu.mypetstore.web.servlet.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewListOrderServlet extends HttpServlet {
    private static final String LIST_ORDERS = "/WEB-INF/jsp/order/ListOrders.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LIST_ORDERS).forward(request,response);
    }
}
