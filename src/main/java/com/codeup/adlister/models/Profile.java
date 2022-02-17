package com.codeup.adlister.models;

import java.sql.ResultSet;

public class Profile {
    private long userId;
    private String firstName;
    private String lastName;
    private String city;

    public Profile() {
    }

    public Profile(long userId) {
        this.userId = userId;
    }

    public Profile(String firstName, String lastName, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public Profile(long userId, String firstName, String lastName, String city) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long id) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void extractProfile(ResultSet rs) {
    }
}
