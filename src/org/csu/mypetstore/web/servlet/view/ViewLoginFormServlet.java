package org.csu.mypetstore.web.servlet.view;

import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewLoginFormServlet extends HttpServlet {
    private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";

    //业务逻辑需求

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(SIGNON).forward(request,response);
    }
}
