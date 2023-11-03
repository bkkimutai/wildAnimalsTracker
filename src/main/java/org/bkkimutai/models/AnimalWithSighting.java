package org.bkkimutai.models;

import org.sql2o.Connection;

import java.util.List;

import static org.bkkimutai.DB.DB.sql2o;

public class AnimalWithSighting {
    private int animalId;
    private String AnimalName;
    private int sightingId;
    private String location;

    public AnimalWithSighting(int animalId,String AnimalName,int sightingId,String location){
        this.animalId = animalId;
        this.AnimalName = AnimalName;
        this.sightingId = sightingId;
        this.location = location;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getAnimalName() {
        return AnimalName;
    }

    public void setAnimalName(String animalName) {
        AnimalName = animalName;
    }

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public static List<AnimalWithSighting> getAllIAnimalsWithsightings() {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(
                            "SELECT a.animalId, a.animalName, s.location " + // Add a space after 's.location'
                                    "FROM animals a " +
                                    "INNER JOIN sightings s ON a.animalId = s.animalId;")
                    .executeAndFetch(AnimalWithSighting.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

}
