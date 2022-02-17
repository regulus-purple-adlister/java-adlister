package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "controllers.EditAdServlet", urlPatterns = "/ads/update_ad")
public class UpdateAdServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long adId = Long.parseLong(request.getParameter("update"));
        Ad ad = DaoFactory.getAdsDao().getAd(adId);
        // retrieve ad's associated categories and convert them into a comma-seperated string
        List<Category> categories = DaoFactory.getCategoriesDao().getCatsForAdId(ad.getId());
        String catString = categories.stream()
                .map(Category::getName)
                .collect(Collectors.joining(", "));

        request.setAttribute("title", ad.getTitle());
        request.setAttribute("description", ad.getDescription());
        request.setAttribute("category", catString);

        request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        try {
            Ad ad = new Ad(id, title, description);
            //DaoFactory.getAdsDao().update(ad);
            request.getSession().setAttribute("ad", ad);
            response.sendRedirect("/profile");
        } catch (Exception e) {
            response.sendRedirect("/profile");
        }
    }


}
