package org.bkkimutai.models;

import org.bkkimutai.DB.DB;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

public class SightingWithLocation {
    private int sightingId;
    private int animalId;
    private String animalName;
    private int locationId;
    private int rangerId;
    private String locationName;
    private Timestamp timestamp;

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getRangerId() {
        return rangerId;
    }

    public void setRangerId(int rangerId) {
        this.rangerId = rangerId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    public static List<SightingWithLocation> getSightingsWithLocation() {
        try (Connection connection = DB.sql2o.open()) {
            return connection.createQuery(
                            "SELECT " +
                                    "  s.sightingId, " +
                                    "  s.animalId, " +
                                    "  s.locationId, " +
                                    "  s.rangerId, " +
                                    "  l.locationName, " +
                                    "  s.timestamp " +
                                    "FROM sightings s " +
                                    "INNER JOIN locations l ON s.locationId = l.locationId")
                    .executeAndFetch(SightingWithLocation.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
    public static SightingWithLocation findSightingWithLocation(int sightingId) {
        String sql = "SELECT " +
                "s.sightingId, " +
                "s.animalId, " +
                "s.locationId, " +
                "s.rangerId, " +
                "l.locationName, " +
                "s.timestamp " +
                "FROM sightings s " +
                "INNER JOIN locations l ON s.locationId = l.locationId " +
                "WHERE s.sightingId = :sightingId;";

        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("sightingId", sightingId)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(SightingWithLocation.class);
        }
    }
    public static List<SightingWithLocation> getAnimalsByLocation(int locationId) {
        String sql = "SELECT s.sightingId, s.animalId, s.locationId, s.rangerId, l.locationName, s.timestamp, a.animalName " +
                "FROM sightings s " +
                "INNER JOIN locations l ON s.locationId = l.locationId " +
                "INNER JOIN animals a ON s.animalId = a.animalId " +
                "WHERE s.locationId = :locationId";

        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("locationId", locationId)
                    .executeAndFetch(SightingWithLocation.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }


}
