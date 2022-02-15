package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface Users {
    User findByUsername(String username);
    Long insert(User user) throws SQLIntegrityConstraintViolationException;
}
