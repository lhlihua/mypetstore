package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterNewAccountServlet extends HttpServlet {
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String NEWACCOUNTFORM = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    private static final String ERROR = "两次密码输入不一致";
    private AccountService accountService;
    private Account account;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountService = new AccountService();
        account = new Account();
        getMessageIntoAccount(request,response,account);
        if(account != null)
            accountService.insertAccount(account);
        request.getRequestDispatcher(SIGNON).forward(request,response);
    }

    private void getMessageIntoAccount(HttpServletRequest request,HttpServletResponse response,Account account)throws ServletException, IOException{
        HttpSession session = request.getSession();
        account.setUsername(request.getParameter("username"));

        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");
        if(password.equals(repeatedPassword)){
            account.setPassword(password);
        }else{
            session.setAttribute("message",ERROR);
            account = null;
            request.getRequestDispatcher(NEWACCOUNTFORM).forward(request,response);
        }
        String firstName = request.getParameter("firstName");
        account.setFirstName(firstName);
        String lastName = request.getParameter("lastName");
        account.setLastName(lastName);
        String email = request.getParameter("email");
        account.setEmail(email);
        String phone = request.getParameter("phone");
        account.setPhone(phone);
        String address1 = request.getParameter("address1");
        account.setAddress1(address1);
        String address2 = request.getParameter("address2");
        account.setAddress2(address2);
        String city = request.getParameter("city");
        account.setCity(city);
        String state = request.getParameter("state");
        account.setState(state);
        String zip = request.getParameter("zip");
        account.setZip(zip);
        String country = request.getParameter("country");
        account.setCountry(country);
        String languagePreference = request.getParameter("languagePreference");
        account.setLanguagePreference(languagePreference);
        String favouriteCategoryId = request.getParameter("favouriteCategoryId");
        account.setFavouriteCategoryId(favouriteCategoryId);
        Boolean listOption = Boolean.parseBoolean(request.getParameter("listOption"));
        account.setListOption(listOption);
        Boolean bannerOption = Boolean.parseBoolean(request.getParameter("bannerOption"));
        account.setBannerOption(bannerOption);
    }
}
