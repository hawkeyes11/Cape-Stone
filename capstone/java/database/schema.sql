BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS group_favorites;
DROP TABLE IF EXISTS user_favorites;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE groups (
    group_id int NOT NULL UNIQUE,
    location varchar(50) NOT NULL,
    expiration_date DATE NOT NULL,

    CONSTRAINT group_id PRIMARY KEY (group_id)
);

CREATE TABLE group_favorites (
    group_id int NOT NULL,
    restaurant_id varchar(69) NOT NULL,

    FOREIGN KEY (group_id) REFERENCES groups(group_id)
);

CREATE TABLE user_favorites (
    user_id int NOT NULL,
    restaurant_id varchar(69) NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE user_categories (
    user_id int NOT NULL,
    category varchar(69) NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


COMMIT TRANSACTION;
