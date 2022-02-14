USE adlister_db;

DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                       username VARCHAR(250) NOT NULL,
                       email VARCHAR(250) NOT NULL,
                       password VARCHAR(250) NOT NULL,
                       PRIMARY KEY (id),
                       UNIQUE (username)
);

CREATE TABLE ads (
                     id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                     user_id INT UNSIGNED NOT NULL,
                     title  VARCHAR(250) NOT NULL,
                     description TEXT NOT NULL,
                     PRIMARY KEY (id),
                     FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE categories (
                            id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                            name VARCHAR(250) NOT NULL,
                            PRIMARY KEY (id)
);

-- TODO: create many to many table that links categories to ads

