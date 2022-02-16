package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.AdCat;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdCatsDao implements AdCats {
    private Connection connection;

    public MySQLAdCatsDao(Config config) {
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
    public List<AdCat> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdCatsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(long catId, long adId) {
        String query = "INSERT INTO ad_cat(category_id, ad_id) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, catId);
            stmt.setLong(2, adId);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return 1L;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new ad-cat link", e);
        }
    }

    private List<AdCat> createAdCatsFromResults(ResultSet rs) throws SQLException {
        List<AdCat> adCats = new ArrayList<>();
        while (rs.next()) {
            adCats.add(extractAd(rs));
        }
        return adCats;
    }

    private AdCat extractAd(ResultSet rs) throws SQLException {
        return new AdCat(
                rs.getLong("ad_id"),
                rs.getLong("category_id")
        );
    }
}
