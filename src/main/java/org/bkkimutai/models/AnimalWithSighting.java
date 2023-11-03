package org.bkkimutai.models;

import org.sql2o.Connection;

import java.util.List;

import static org.bkkimutai.DB.DB.sql2o;

public class AnimalWithSighting {
    private int animalId;
    private String animalName;
    private int locationId;
    private String locationName;
    private int sightingId;
    private int rangerId;
    private String rangerName;

//    public AnimalWithSighting(int animalId,String AnimalName,int sightingId,String locationName){
//        this.animalId = animalId;
//        this.AnimalName = AnimalName;
//        this.sightingId = sightingId;
//        this.locationName = locationName;
//    }

    public static List<AnimalWithSighting> getAllAnimalsWithSightings() {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(
                            "SELECT a.animalId, a.animalName, s.locationId, l.locationName, s.sightingId, s.rangerId, r.rangerName " +
                                    "FROM animals a " +
                                    "INNER JOIN sightings s ON a.animalId = s.animalId " +
                                    "INNER JOIN locations l ON s.locationId = l.locationId " +
                                    "INNER JOIN rangers r ON s.rangerId = r.rangerId;")
                    .executeAndFetch(AnimalWithSighting.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }


    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getRangerId() {
        return rangerId;
    }

    public void setRangerId(int rangerId) {
        this.rangerId = rangerId;
    }

    public String getRangerName() {
        return rangerName;
    }

    public void setRangerName(String rangerName) {
        this.rangerName = rangerName;
    }
}
