package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.service.CodeImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ValiImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                response.setHeader("cache-control", "no-cache");

        response.setHeader("Expire", "-1"); // >0为缓存

        response.setHeader("pragma", "no-cache");


        CodeImage codeImage = new CodeImage();
        BufferedImage bufferedImage =codeImage.getBufferedImage();
        HttpSession session = request.getSession();
        session.setAttribute("imagetext",codeImage.getText());
        try {

            ImageIO.write(bufferedImage, "GIF",response.getOutputStream());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
