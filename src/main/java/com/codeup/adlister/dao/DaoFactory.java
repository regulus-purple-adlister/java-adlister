package com.codeup.adlister.dao;

public class DaoFactory {
    private static Ads adsDao;
    private static Users usersDao;
    private static Categories categoriesDao;
    private static AdCats adCatsDao;
    private static Profiles profilesDao;
    private static Config config = new Config();

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    public static Users getUsersDao() {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao(config);
        }
        return usersDao;
    }

    public static Categories getCategoriesDao() {
        if (categoriesDao == null) {
            categoriesDao = new MySQLCategoriesDao(config);
        }
        return categoriesDao;
    }

    public static AdCats getAdCatDao() {
        if (adCatsDao == null) {
            adCatsDao = new MySQLAdCatsDao(config);
        }
        return adCatsDao;
    }

    public static Profiles getProfilesDao() {
        if (profilesDao == null) {
            profilesDao = new MySQLProfilesDao(config);
        }
        return profilesDao;
    }
}
