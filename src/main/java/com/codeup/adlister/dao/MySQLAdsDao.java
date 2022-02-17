package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
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
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    public Ad getAd(long id) {
        Ad ad = null;
        try {
            String sql = "SELECT * FROM ads WHERE id = ?;";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            ad = extractAd(rs);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
        return ad;
    }

    @Override
    public List<Ad> getAdsForUser(long userId) {
        List<Ad> ads = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ads WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            ads = createAdsFromResults(rs);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
        return ads;
    }

    @Override
    public List<Ad> searchAds(String query, String type) {
        List<Ad> ads = new ArrayList<>();
        try {
            ResultSet rs = null;
            if (type.equals("title")) {
                rs = searchAdsByTitle(query);
            } else if (type.equals("user")) {
                rs = searchAdsByUser(query);
            } else if (type.equals("category")) {
                rs = searchAdsByCategory(query);
            }
            ads = createAdsFromResults(rs);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
        return ads;
    }

    @Override
    public boolean updateAd(Ad ad) {
        try {
            String sql = "UPDATE ads SET title = ?, description = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getDescription());
            stmt.setLong(3, ad.getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating ad of id " + ad.getId());
        }
        return false;
    }

    @Override
    public boolean deleteAd(long id) {
        try {
            String sql = "DELETE FROM ads WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, id);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting ad of id " + id);
        }
        return false;
    }


    private ResultSet searchAdsByTitle(String query) throws SQLException {
        String sql = "SELECT * FROM ads WHERE title LIKE ?";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, '%' + query + '%');
        return stmt.executeQuery();
    }

    private ResultSet searchAdsByUser(String query) throws SQLException {
        String sql = "SELECT * FROM ads JOIN users u on ads.user_id = u.id WHERE u.username LIKE ?";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, '%' + query + '%');
        return stmt.executeQuery();
    }

    private ResultSet searchAdsByCategory(String query) throws SQLException {
        String sql = "SELECT * FROM ads JOIN ad_cat ac on ads.id = ac.ad_id JOIN categories c on c.id = ac.category_id WHERE c.name LIKE ?";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, '%' + query + '%');
        return stmt.executeQuery();
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
}
