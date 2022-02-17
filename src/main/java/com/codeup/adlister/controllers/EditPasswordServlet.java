package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;
import org.mindrot.jbcrypt.BCrypt;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.EditPasswordServlet", urlPatterns = "/editPassword")
public class EditPasswordServlet extends HttpServlet {
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/editPassword.jsp").forward(request, response);
    }
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        String confirmNewPassword = request.getParameter("confirm_new_password");
        String hash = Password.hash(newPassword);
        User user = (User)request.getSession().getAttribute("user");

        if(!BCrypt.checkpw(oldPassword, user.getPassword()) || !newPassword.equals(confirmNewPassword)) {
            response.sendRedirect("/editPassword");
        } else {
            user.setPassword(hash);
            DaoFactory.getUsersDao().updatePassword(user);
            response.sendRedirect("/login");
        }
    }
}
