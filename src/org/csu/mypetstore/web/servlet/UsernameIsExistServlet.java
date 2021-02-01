package org.csu.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.util.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsernameIsExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        AccountService accountService = new AccountService();
        Account account = accountService.getAccount(username);
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Result result = new Result();
        if(account == null){
            //用户名可用
            result.setCode(0);
            result.setMsg("Not Exist");
        }else{
            result.setCode(1);
            result.setMsg("Exist");
        }
        String str = JSON.toJSONString(result);
        out.print(str);
        out.flush();
        out.close();
        System.out.println("zhjhhh");
    }
}
