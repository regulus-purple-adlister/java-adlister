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
        String query = "INSERT INTO profile(user_id) VALUES (?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, profile.getUserId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return profile.getUserId();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("error inserting profile");
        }
    }

    public Long update(Profile profile) {
        String query = "UPDATE profile SET first_name = ?, last_name = ?, city = ? WHERE user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, profile.getFirstName());
            stmt.setString(2, profile.getLastName());
            stmt.setString(3, profile.getCity());
            stmt.setLong(4, profile.getUserId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return profile.getUserId();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("error updating profile");
        }
    }

    private Profile extractProfile(ResultSet rs) throws SQLException {
        return new Profile(
                rs.getLong("user_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
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
            profile = extractProfile(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error" + e);
        }
        return profile;
    }

    public Long delete(Profile profile) {
        try {
            String profileQuery = "DELETE profile, users FROM users LEFT JOIN profile on profile.user_id = users.id WHERE user_id = ?";
//            String userQuery = "DELETE FROM users WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(profileQuery, Statement.RETURN_GENERATED_KEYS);
//            PreparedStatement stmt2 = connection.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, profile.getUserId());
            stmt.executeUpdate();
//            stmt2.setLong(1, id);
            return profile.getUserId();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting profile #" + profile.getUserId(), e);
        }
    }


}
