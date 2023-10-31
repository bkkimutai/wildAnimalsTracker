package org.bkkimutai.models;

import org.bkkimutai.DB.DB;
import org.bkkimutai.DB.DBManagement;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;


public class Sightings implements DBManagement {
    private int sightingId;
    private int animalId;
    private String location;
    private int rangerId;
    private Timestamp timestamp;

    public Sightings( int animalId, String location, int rangerId){
        this.animalId = animalId;
        this.location = location;
        this.rangerId = rangerId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRangerId() {
        return rangerId;
    }

    public void setRangerId(int rangerId) {
        this.rangerId = rangerId;
    }

    public String getTimestamp() {
        return String.format("%1$TD %1$TR", timestamp);
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public void save() {
        try (Connection con = DB.sql2o.beginTransaction()) {
            String sql = "INSERT INTO sightings (animalId, location, rangerId, Timestamp) VALUES (:animalId, :location, :rangerId, now());";
            con.createQuery(sql)
                    .addParameter("animalId", this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("rangerId", this.rangerId)
                    .executeUpdate();
            String idQuery = "SELECT lastval()";
            this.sightingId = con.createQuery(idQuery).executeScalar(Integer.class);
            con.commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public static List<Sightings> all() {
        String sql = "SELECT * FROM sightings ORDER BY timestamp DESC;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sightings.class);
        }
    }
    public static List<Sightings> allByAnimal(int animalId) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE animalId = :animalId ORDER BY timestamp DESC";
            return con.createQuery(sql)
                    .addParameter("animalId", animalId)
                    .executeAndFetch(Sightings.class);
        }
    }
}
