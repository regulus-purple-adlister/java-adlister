package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Profile;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet("/updateprofile")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("/login");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/updateprofile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String city = req.getParameter("city");

        User user = (User) req.getSession().getAttribute("user");
        Profile profile = new Profile(
                user.getId(),
                req.getParameter("firstname"),
                req.getParameter("lastname"),
                req.getParameter("city")
        );

        try {
            long profileId = DaoFactory.getProfilesDao().update(profile);
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
    resp.sendRedirect("/profile");
    }
}
