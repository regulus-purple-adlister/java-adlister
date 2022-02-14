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
        // bad quick solution needs to be replaced with a dao method and real SQL query
        List<Ad> ads = DaoFactory.getAdsDao().all();
        for (Ad ad : ads) {
            // if any ads' ids match the path string, we send the ad info to a template page and forward user
            if (String.valueOf(ad.getId()).equals(pathString)) {
                request.setAttribute("ad", ad);
                request.getRequestDispatcher("/WEB-INF/ads/ad.jsp").forward(request, response);
            }
        }
        // if no matches are found, redirect to the ads index
        response.sendRedirect("/ads");
    }
}
