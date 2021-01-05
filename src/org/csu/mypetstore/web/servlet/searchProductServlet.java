package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class searchProductServlet extends HttpServlet{
    private static final String SEARCH_PRODUCT = "/WEB-INF/jsp/catalog/SearchProducts.jsp";
    private String keyword;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        keyword = req.getParameter("keyword");

        CatalogService service = new CatalogService();
        List<Product> productList = service.searchProductList(keyword);
        System.out.println(productList);

        HttpSession session = req.getSession();
        session.setAttribute("productList",productList);

        req.getRequestDispatcher(SEARCH_PRODUCT).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
