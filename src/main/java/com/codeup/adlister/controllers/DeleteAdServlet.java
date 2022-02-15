package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.Ads;
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

@WebServlet(name = "controllers.DeleteAdServlet", urlPatterns = "/ads/delete_ad")
public class DeleteAdServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Ads adsDao = DaoFactory.getAdsDao();
        //adsDao.delete(id);
        response.sendRedirect("/ads");
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null) {
//            response.sendRedirect("/login");
//            return;
//        }
//        String query = request.getQueryString();
//        Long id = Long.valueOf(query.substring(3));
//        DaoFactory.getAdsDao().delete(id);
//        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        System.out.println(adId);
//        DaoFactory.getAdsDao().delete(adId);
//        response.sendRedirect("/profile");

    }

}
