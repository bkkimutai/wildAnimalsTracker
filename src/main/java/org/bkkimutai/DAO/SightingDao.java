package org.bkkimutai.DAO;

import org.bkkimutai.DB.DB;
import org.bkkimutai.models.Animals;
import org.bkkimutai.models.EndangeredAnimals;
import org.bkkimutai.models.Sightings;
import org.sql2o.Connection;

import static org.bkkimutai.DB.DB.sql2o;

public class SightingDao {

    public static void addSightings(Sightings newSightings) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("INSERT INTO sightings (animalId, locationId, rangerId, timestamp) VALUES (:animalId,:locationId,:rangerId,now());")
                    .addParameter("animalId", newSightings.getAnimalId())
                    .addParameter("locationId", newSightings.getLocationId())
                    .addParameter("rangerId", newSightings.getRangerId())
                    .executeUpdate();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void addAnimal(Animals newAnimal) {
        try (Connection connection = DB.sql2o.beginTransaction()) {
            String sql = "INSERT INTO animals (animalName, rangerId, type) VALUES (:animalName, :rangerId, :type);";
            int animalId = connection.createQuery(sql, true)  // Set returnGeneratedKeys to true
                    .addParameter("animalName", newAnimal.getAnimalName())
                    .addParameter("rangerId", newAnimal.getRangerId())
                    .addParameter("type", newAnimal.getType())
                    .executeUpdate()
                    .getKey(Integer.class);
            newAnimal.setAnimalId(animalId);
            connection.commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }


    public static void addEndangeredAnimal(EndangeredAnimals newEndangeredAnimal) {

        try (Connection connection = DB.sql2o.beginTransaction()) {
            String sql = "INSERT INTO animals (animalName, rangerId, type, health, age) VALUES (:animalName, :rangerId, :type, :health, :age)";
            int animalId = connection.createQuery(sql, true)
                    .addParameter("animalName", newEndangeredAnimal.getAnimalName())
                    .addParameter("rangerId", newEndangeredAnimal.getRangerId())
                    .addParameter("type", newEndangeredAnimal.getType())
                    .addParameter("health", newEndangeredAnimal.getHealth())
                    .addParameter("age", newEndangeredAnimal.getAge())
                    .executeUpdate()
                    .getKey(Integer.class);
            newEndangeredAnimal.setAnimalId(animalId);
            connection.commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
}
