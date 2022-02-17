USE adlister_db;

DROP TABLE IF EXISTS ad_cat;
DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;

CREATE TABLE IF NOT EXISTS users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS ads (
     id INT UNSIGNED NOT NULL AUTO_INCREMENT,
     user_id INT UNSIGNED NOT NULL,
     title  VARCHAR(250) NOT NULL,
     description TEXT NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS categories (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ad_cat (
    category_id INT UNSIGNED NOT NULL,
    ad_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (category_id, ad_id),
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE,
    FOREIGN KEY (ad_id) REFERENCES ads (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS profile (
    user_id    INT UNSIGNED NOT NULL,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    city       VARCHAR(50),
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);



