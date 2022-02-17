package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface Users {
    User findByUsername(String username);
    User findByEmail(String email);
    Long insert(User user) throws SQLIntegrityConstraintViolationException;
    User findById(long userId);
    void updatePassword(User user);
}
