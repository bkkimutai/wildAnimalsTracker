SET MODE PostgresSQL;

CREATE TABLE IF NOT EXISTS animals(
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    rangerId INT,
    type VARCHAR,
    health VARCHAR,
    age VARCHAR
);

CREATE TABLE IF NOT EXISTS sightings (
   sightingId int PRIMARY KEY auto_increment,
   animalId int,
   location VARCHAR,
   rangerId VARCHAR,
   Timestamp timestamp;
);