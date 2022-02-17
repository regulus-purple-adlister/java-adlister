package com.codeup.adlister.dao;

import com.codeup.adlister.models.Profile;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLProfilesDao {
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
            stmt.setString(1, profile.getLastName());
            stmt.setString(1, profile.getCity());
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
        if (! rs.next()) {
            return null;
        }
        return new Profile(
                rs.getLong("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("city")
        );
    }
}
