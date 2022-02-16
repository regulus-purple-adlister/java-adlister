package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;

import static java.lang.System.out;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        // validate input
        boolean formErrors = false;
        // check if username is valid
        if (username.isEmpty()) {
            request.setAttribute("userError", "Username is required.");
            formErrors = true;
        } else if (DaoFactory.getUsersDao().findByUsername(username) != null) {
            request.setAttribute("userError", "Username is already taken.");
            formErrors = true;
        }
        // check if email is valid
        if (email.isEmpty()) {
            request.setAttribute("emailError", "Email is required.");
            formErrors = true;
        }
        // check if password is valid
        if (password.isEmpty()) {
            request.setAttribute("passError", "Password is required.");
            formErrors = true;
        } else if (! password.equals(passwordConfirmation)) {
            request.setAttribute("passError", "Passwords must match.");
            formErrors = true;
        }

        if (formErrors) { // if any input is invalid, redirect back to register form with the new error messages
            // save the contents of fields besides password (for security) so the form is sticky
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            try {
                request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } else { // if everything was fine, let's create the user and add them to database
            User user = new User(username, email, password);
            try {
                DaoFactory.getUsersDao().insert(user);
                response.sendRedirect("/login");
            } catch (SQLIntegrityConstraintViolationException e) {
                response.sendRedirect("/register");
            }
        }
    }
}
