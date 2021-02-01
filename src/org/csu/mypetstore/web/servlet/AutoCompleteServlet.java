package org.csu.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AutoCompleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        CatalogService service = new CatalogService();
        List<Product> productList = service.searchProductList(keyword);
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        String str = JSON.toJSONString(productList);
        out.print(str);
        out.flush();
        out.close();

    }
}
