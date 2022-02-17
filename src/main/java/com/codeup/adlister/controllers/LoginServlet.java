package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = null;

        // check for valid user by username or email
        User userByName = DaoFactory.getUsersDao().findByUsername(username);
        if (userByName != null) {
            user = userByName;
        } else {
            user = DaoFactory.getUsersDao().findByEmail(username);
        }

//        if (user == null) {
//            response.sendRedirect("/login");
//            return;
//        }
//        boolean validAttempt = user != null || Password.check(password, user.getPassword());

        if (user != null) { // if user exists...
            if (Password.check(password, user.getPassword())) { // if passwords match...
                // log user in!
                request.getSession().setAttribute("user", user);
                response.sendRedirect("/profile");
                return;
            }
        }
        // if any of these failed, login was bad so give error to user
        request.setAttribute("username", username);
        request.setAttribute("loginError", "The given username and password do not match any record in our database.");
        try {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
