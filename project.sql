CREATE TABLE users (
                       id serial  primary key,
                       username varchar(255),
                       name varchar(255),
                       surname varchar(255),
                       password varchar(255),
                       role varchar(255)
);
CREATE TABLE groups (
                        id serial primary key,
                        name varchar(255),
                        year int
);
CREATE TABLE clubs(
                      id serial primary key,
                      name varchar(255),
                      description varchar(255)
);

CREATE TABLE clubstudent (
                             id serial primary key,
                             clubid int,
                             studentid int
);
CREATE TABLE groupstudent (
                              id serial primary key,
                              groupid int,
                              studentid int
);


CREATE TABLE news (
                      id int primary key,
                      title varchar(255),
                      description varchar(255),
                      publisher int
);


CREATE TABLE events (
                        id serial primary key,
                        event varchar(255),
                        description varchar(255)
);
CREATE TABLE eventstudent(
                             id int primary key,
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



