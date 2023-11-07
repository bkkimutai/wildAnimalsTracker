# Wildlife Tracker
#### Wildlife Tracker, April 28th 2019
#### By **Benjamin Kimutai**
## Description

```bash
An application that allows Rangers to track wildlife sightings in a geographical location.
```

## Technologies Used
- Java: The primary programming language for the application.
- PostgreSQL: The relational database management system used for data storage.
- Spark Java: A lightweight web framework for building web applications in Java.
- Sql2o: A database library for Java that simplifies database interaction.
- Handlebars: A templating engine used for rendering views.
- Bootstrap: A front-end framework for building responsive and visually appealing web pages.
- HTML: The standard markup language for creating web pages.
- CSS: Cascading Style Sheets for styling web pages.

## Installation
* `git clone <https://github.com/bkkimutai/wildAnimalsTracker.git>` this repository
* `cd WildAnimalTracker`

## Tests

- To run test, run files under tests/java package


## SQL
```bash
1. Launch postgres

2. SET MODE PostgresSQL;

Run these commands
3. CREATE DATABASE IF NOT EXISTS wildlife_tracker;
4. \c wildlife_tracker;
5. CREATE TABLE IF NOT EXISTS animals(
    animalId serial PRIMARY KEY,
    animalName VARCHAR,
    rangerId INT,
    type VARCHAR,
    health VARCHAR,
    age VARCHAR);
6. CREATE TABLE IF NOT EXISTS sightings (
   sightingId serial PRIMARY KEY,
   animalId int,
   locationId int,
   rangerId int,
   Timestamp timestamp);
5. CREATE TABLE IF NOT EXISTS rangers (
   rangerId serial PRIMARY KEY,
   rangerName VARCHAR,
   email VARCHAR,
   badgeNumber VARCHAR);

6. CREATE TABLE IF NOT EXISTS locations (
   locationId serial PRIMARY KEY,
   locationName VARCHAR);
7. CREATE DATABASE IF NOT EXISTS wildlife_tracker_test WITH TEMPLATE wildlife_tracker;
```
## Requirements
```bash
The applications allow users to do the following:

1.Add a new non-endangered animal
 
2.Add a new endangered animal

3.Add an animal Sighting.
```
## Known Bugs
- Currently, there is no real tests done for this project.
- At the time this project was done, responsiveness was not a major concern.
- The project is currently not being maintained.
## Contact Details
```bash
You can contact me at kbmsama90@gmail.com
```
## Live Link
[Visit the live link](https://bkkimutai.github.io/wildAnimalsTracker/)

## License
This projects has a MIT License [found here](LICENSE)

