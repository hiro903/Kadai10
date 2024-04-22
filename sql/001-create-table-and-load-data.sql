DROP TABLE IF EXISTS staff;
CREATE TABLE staff (
    id int unsigned AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    nearest_station VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
    );

INSERT INTO staff (name, date_of_birth, nearest_station) VALUES ('anna', '2000-07-01', 'Tokyo');
INSERT INTO staff (name, date_of_birth, nearest_station) VALUES ('erika', '2005-08-13', 'Meguro');
INSERT INTO staff (name, date_of_birth, nearest_station) VALUES ('nana', '1998-10-25', 'Kichijoji');
INSERT INTO staff (name, date_of_birth, nearest_station) VALUES ('sachi', '1996-12-21', 'Shinagawa');
