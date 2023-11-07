SET MODE PostgresSQL;
CREATE DATABASE IF NOT EXISTS wildlife_tracker;

CREATE TABLE IF NOT EXISTS animals(
    animalId serial PRIMARY KEY,
    animalName VARCHAR,
    rangerId INT,
    type VARCHAR,
    health VARCHAR,
    age VARCHAR
);

CREATE TABLE IF NOT EXISTS sightings (
   sightingId serial PRIMARY KEY,
   animalId int,
   locationId int,
   rangerId int,
   Timestamp timestamp);
CREATE TABLE IF NOT EXISTS rangers (
   rangerId serial PRIMARY KEY,
   rangerName VARCHAR,
   email VARCHAR,
   badgeNumber VARCHAR);

CREATE TABLE IF NOT EXISTS locations (
   locationId serial PRIMARY KEY,
   locationName VARCHAR);
CREATE DATABASE IF NOT EXISTS wildlife_tracker_test WITH TEMPLATE wildlife_tracker;