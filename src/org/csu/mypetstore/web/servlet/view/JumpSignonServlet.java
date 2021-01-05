package org.csu.mypetstore.web.servlet.view;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JumpSignonServlet extends HttpServlet {
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    int times = 0;
    private Boolean accountLogin;
    private List<String> languages;
    private List<String> favouriteCategoryIds;
    private List<String> cardList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountLogin = false;
        HttpSession session = request.getSession();
        languages = new ArrayList<>();
        languages.add("English");
        languages.add("Chinese");
        languages.add("Franch");

        favouriteCategoryIds = new ArrayList<>();
        favouriteCategoryIds.add("FISH");
        favouriteCategoryIds.add("DOGS");
        favouriteCategoryIds.add("REPTILES");
        favouriteCategoryIds.add("CATS");
        favouriteCategoryIds.add("BIRDS");

        cardList = new ArrayList<String>();
        cardList.add("Visa");
        cardList.add("MasterCard");
        cardList.add("American Express");

        session.setAttribute("languages",languages);
        session.setAttribute("favouriteCategoryIds",favouriteCategoryIds);
        session.setAttribute("creditCardTypes",cardList);

        session.setAttribute("times",times);
        session.setAttribute("inputname",null);
        session.setAttribute("inputpassword",null);
        session.setAttribute("accountLogin",accountLogin);
        request.getRequestDispatcher(SIGNON).forward(request,response);
    }
}
