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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "controllers.EditAdServlet", urlPatterns = "/ads/update_ad")
public class UpdateAdServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long adId = Long.parseLong(request.getParameter("update"));
        getServletContext().setAttribute("adId", adId);
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
        long id = (long) getServletContext().getAttribute("adId");
        // check all fields have required info
        boolean formErrors = false;
        if (request.getParameter("title").isEmpty()) {
            request.setAttribute("titleError", "Title is required.");
            formErrors = true;
        }
        if (request.getParameter("description").isEmpty()) {
            request.setAttribute("descriptionError", "Description is required.");
            formErrors = true;
        }
        if (request.getParameter("category").isEmpty()) {
            request.setAttribute("categoryError", "Category/categories are required.");
            formErrors = true;
        }

        if (formErrors) {
            // save the contents of fields so the form is sticky
            request.setAttribute("title", request.getParameter("title"));
            request.setAttribute("description", request.getParameter("description"));
            request.setAttribute("category", request.getParameter("category"));
            try {
                request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } else {
            User user = (User) request.getSession().getAttribute("user");
            Ad ad = new Ad(
                    id,
                    user.getId(),
                    request.getParameter("title"),
                    request.getParameter("description")
            );

            DaoFactory.getAdsDao().updateAd(ad);

            // clear associated categories from adCats table
            DaoFactory.getCategoriesDao().removeAssociations(id);

            // process categories
            List<String> catStrings = Arrays.asList(request.getParameter("category").split("\\s*,\\s*"));
            for (String str : catStrings) {
                Category cat = new Category(str);
                long catId = DaoFactory.getCategoriesDao().insert(cat);
                DaoFactory.getAdCatDao().insert(catId, ad.getId());
            }
            response.sendRedirect("/ads/" + id);
        }
    }
}
