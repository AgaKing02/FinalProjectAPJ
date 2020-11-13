-- First, create a database, then add this sql to the database.
-- !!! do not forget to write the name of the database in the connection
-- Для понимания структуры таблиц используйте bd.png

CREATE TABLE users (
    id serial  PRIMARY KEY NOT NULL,
    username varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    surname varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    role varchar(255)
);

CREATE TABLE groups (
    id serial PRIMARY KEY NOT NULL,
    name varchar(255) NOT NULL,
    year int NOT NULL
);

CREATE TABLE clubs(
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    description varchar(255) NOT NULL
);

CREATE TABLE clubstudent (
    id serial PRIMARY KEY NOT NULL,
    clubid int NOT NULL,
    studentid int NOT NULL
);

CREATE TABLE groupstudent (
    id serial PRIMARY KEY NOT NULL,
    groupid int NOT NULL,
    studentid int NOT NULL
);

CREATE TABLE news (
  id int PRIMARY KEY NOT NULL,
  title varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  publisher int NOT NULL
);

CREATE TABLE events (
  id serial PRIMARY KEY NOT NULL,
  event varchar(255),
  description varchar(255)
);
CREATE TABLE eventstudent(
  id int PRIMARY KEY NOT NULL,
  eventid int,
  studentid int
);
ALTER TABLE groupstudent
    ADD FOREIGN KEY (studentid) REFERENCES users(id);

ALTER TABLE groupstudent
    ADD FOREIGN KEY (groupid) REFERENCES groups(id);

ALTER TABLE news
    ADD FOREIGN KEY (publisher) REFERENCES users(id);

ALTER TABLE eventstudent
    ADD FOREIGN KEY (studentid) REFERENCES users(id);

ALTER TABLE eventstudent
    ADD FOREIGN KEY (eventid) REFERENCES events(id);


ALTER TABLE clubstudent
    ADD FOREIGN KEY (studentid) REFERENCES users(id);

ALTER TABLE clubstudent
    ADD FOREIGN KEY (clubid) REFERENCES clubs(id);