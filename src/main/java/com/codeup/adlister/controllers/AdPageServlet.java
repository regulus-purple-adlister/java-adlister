package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.AdPageServlet", urlPatterns = "/ads/*")
public class AdPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathString = request.getPathInfo().substring(1);
        try {
            long id = Long.parseLong(pathString);
            Ad ad = DaoFactory.getAdsDao().getAd(id);
            request.setAttribute("ad", ad);
            request.getRequestDispatcher("/WEB-INF/ads/ad.jsp").forward(request, response);
        } catch (NumberFormatException | NullPointerException e) {
            // if no matches are found, redirect to the ads index
            response.sendRedirect("/ads");
        }
    }
}
