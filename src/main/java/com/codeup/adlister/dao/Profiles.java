package com.codeup.adlister.dao;

import com.codeup.adlister.models.Profile;
import com.codeup.adlister.models.User;

import java.sql.SQLIntegrityConstraintViolationException;

public interface Profiles {
    Long insert(Profile profile) throws SQLIntegrityConstraintViolationException;

}
