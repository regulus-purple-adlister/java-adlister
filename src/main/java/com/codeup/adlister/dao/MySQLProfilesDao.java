package com.codeup.adlister.dao;

import com.codeup.adlister.models.Profile;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLProfilesDao implements Profiles {
    private Connection connection;

    public MySQLProfilesDao(Config config) {
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

    public Long insert(Profile profile) {
        String query = "INSERT INTO profile(first_name, last_name, city) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, profile.getFirstName());
            stmt.setString(2, profile.getLastName());
            stmt.setString(3, profile.getCity());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("error updating profile");
        }
    }

    private Profile extractProfile(ResultSet rs) throws SQLException {
        return new Profile(
                rs.getLong("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("city")
        );
    }

    public Profile getProfile(long id) {
        Profile profile = null;
        try {
            String sql = "SELECT * FROM profile WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            profile.extractProfile(rs);
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
        return profile;
    }


}
