package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.SeachAdsServlet", urlPatterns = "/search/")
public class SearchAdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("search");
        String type = request.getParameter("type");
        if (query != null && type != null) {
            if (query.isEmpty()) {
                query = "*"; // this may not work and might have to be handled in ads dao instead
            }
            System.out.println(query);
            System.out.println(type);
            // temp -- replace .all() with dao method to match ads by search parameter
            request.setAttribute("ads", DaoFactory.getAdsDao().all());
            request.getRequestDispatcher("/WEB-INF/ads/results.jsp").forward(request, response);
        } else {
            response.sendRedirect("/ads");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
