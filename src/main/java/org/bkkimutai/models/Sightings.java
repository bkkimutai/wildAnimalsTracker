package org.bkkimutai.models;

import org.bkkimutai.DB.DB;
import org.bkkimutai.DB.DBManagement;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;


public class Sightings implements DBManagement {
    private int sightingId;
    private int animalId;
    private int locationId;
    private int rangerId;
    private Timestamp timestamp;

    public Sightings(int animalId, int locationId, int rangerId){
        this.animalId = animalId;
        this.locationId = locationId;
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

    public String getTimestamp() {
        return String.format("%1$TD %1$TR", timestamp);
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    @Override
//    public void save() {
//        try (Connection con = DB.sql2o.beginTransaction()) {
//            String sql = "INSERT INTO sightings (animalId, location, rangerId, timestamp) VALUES (:animalId,:location,:rangerId,now());";
//            con.createQuery(sql)
//                    .addParameter("animalId", this.animalId)
//                    .addParameter("location", this.location)
//                    .addParameter("rangerId", this.rangerId)
//                    .executeUpdate();
//            String idQuery = "SELECT lastval()";
//            this.sightingId = con.createQuery(idQuery).executeScalar(Integer.class);
//
//            con.commit();
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalId, locationId, rangerId, timestamp) VALUES (:animalId,:locationId,:rangerId,now());";
            this.sightingId = (int) con.createQuery(sql, true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("locationId", this.locationId)
                    .addParameter("rangerId", this.rangerId)
                    .executeUpdate()
                    .getKey();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public static List<Sightings> all() {
        String sql = "SELECT * FROM sightings;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sightings.class);
        }
    }
    public static List<Sightings> allByAnimal(int animalId) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE sightingId = :sightingId ORDER BY timestamp DESC";
            return con.createQuery(sql)
                    .addParameter("animalId", animalId)
                    .executeAndFetch(Sightings.class);
        }
    }
    public static Sightings find(int sightingId) {
        String sql = "SELECT * FROM sightings WHERE sightingId = :sightingId;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("sightingId", sightingId)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Sightings.class);
        }
    }

    public void update() {
        String sql = "UPDATE sightings SET locationId = :locationId, rangerId = :rangerId WHERE sightingId = :sightingId";
        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("location", locationId)
                    .addParameter("rangerId", rangerId)
                    .addParameter("sightingId", sightingId)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }
}
