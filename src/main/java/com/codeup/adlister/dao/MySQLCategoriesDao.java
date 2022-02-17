package com.codeup.adlister.dao;

import com.codeup.adlister.models.Category;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements Categories {
    private Connection connection = null;

    public MySQLCategoriesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Category> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = stmt.executeQuery();
            return createCategoriesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Category category) {
        // check if category already exists before adding a new one
        Category catExisting = findByCategory(category.getName());
        if (catExisting != null) {
            return catExisting.getId();
        } else {
            try {
                String insertQuery = "INSERT INTO categories(name) VALUES (?)";
                PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, category.getName());

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                return rs.getLong(1);
            } catch (SQLException e) {
                throw new RuntimeException("Error creating a new category.", e);
            }
        }
    }

    @Override
    public List<Category> getCatsForAdId(long id) {
        try {
            String sql = "SELECT * FROM categories cat JOIN ad_cat ac on cat.id = ac.category_id WHERE ac.ad_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return createCategoriesFromResults(rs);
        } catch (SQLException e) {
            System.out.println("Error retrieving categories for ad id " + id);
        }
        // if no categories match the given ad id, return an empty list
        return new ArrayList<>();
    }

    @Override
    public boolean removeAssociations(long id) {
        try {
            String sql = "DELETE FROM ad_cat WHERE ad_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Category extractCategory(ResultSet rs) throws SQLException {
        return new Category(
                rs.getLong("id"),
                rs.getString("name")
        );
    }

    private List<Category> createCategoriesFromResults(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(extractCategory(rs));
        }
        return categories;
    }

    public Category findByCategory(String category) {
        try {
            String query = "SELECT * FROM categories WHERE name = ? LIMIT 1";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractCategory(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding category", e);
        }
    }
}
