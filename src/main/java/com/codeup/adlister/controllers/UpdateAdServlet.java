package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "controllers.EditAdServlet", urlPatterns = "/ads/update_ad")
public class UpdateAdServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getQueryString();
        Long id = Long.valueOf(query.substring(3));

        Ad curAd = DaoFactory.getAdsDao().findOneAd(id);
        request.getSession().setAttribute("ad", curAd);

        request.getRequestDispatcher("/WEB-INF/ads/update_ad.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        try {
            Ad ad = new Ad(id, title, description);
            DaoFactory.getAdsDao().update(ad);
            request.getSession().setAttribute("ad", ad);
            response.sendRedirect("/profile");
        } catch (Exception e) {
            response.sendRedirect("/profile");
        }
    }


}
