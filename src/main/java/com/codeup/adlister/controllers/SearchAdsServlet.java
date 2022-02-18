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

@WebServlet(name = "controllers.SearchAdsServlet", urlPatterns = "/search/")
public class SearchAdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("search");
        String type = request.getParameter("type");
        request.setAttribute("caller", request.getRequestURI());
        if (query != null && type != null) {
            List<Ad> adsResult = DaoFactory.getAdsDao().searchAds(query, type);
            request.setAttribute("ads", adsResult);
            request.getRequestDispatcher("/WEB-INF/ads/results.jsp").forward(request, response);
        } else {
            response.sendRedirect("/ads");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
