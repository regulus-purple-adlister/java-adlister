package com.codeup.adlister.dao;

import com.codeup.adlister.models.Profile;
import com.codeup.adlister.models.User;

import java.sql.SQLIntegrityConstraintViolationException;

public interface Profiles {
    Long update(Profile profile) throws SQLIntegrityConstraintViolationException;
    Long insert(Profile profile) throws SQLIntegrityConstraintViolationException;
    Profile getProfile(long id);
    Long delete(Profile profile);
}
