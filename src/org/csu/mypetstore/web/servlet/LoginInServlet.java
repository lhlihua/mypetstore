package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginInServlet extends HttpServlet {
    private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String WARNING = "用户名或密码输入错误!";
    private static final String MAIN = "/WEB-INF/jsp/catalog/main.jsp";

    private AccountService accountService;
    private Account account;
    private Boolean accountLogin;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        accountService = new AccountService();
        account = accountService.getAccount(username,password);

        session.setAttribute("account",account);

        accountLogin = (Boolean)session.getAttribute("accountLogin");

        //如果account为空,则用户名或密码输入错误
        int num = (int)session.getAttribute("times");
        if(account == null){
            session.setAttribute("messageSignon",WARNING);
            num++;
            session.setAttribute("times",num);
            request.getRequestDispatcher(SIGNON).forward(request,response);
        }
        else if( num >= 3 && !(session.getAttribute("imagetext").equals(request.getParameter("imagetext")))){
            session.setAttribute("inputusername",request.getParameter("username"));
            session.setAttribute("inputpassword",request.getParameter("password"));
            session.setAttribute("messageSignon","验证码输入错误");
            request.getRequestDispatcher(SIGNON).forward(request,response);
        }
        else{
            accountLogin = true;
            session.setAttribute("accountLogin",accountLogin);
            session.setAttribute("messageSignon",null);
            request.getRequestDispatcher(MAIN).forward(request,response);
        }
    }
}
