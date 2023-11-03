package org.bkkimutai.models;

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
}
