package org.bkkimutai.models;

import org.bkkimutai.DB.DB;
import org.bkkimutai.DB.DBManagement;
import org.sql2o.Connection;

import java.util.List;

public class Animals extends AnimalAbstract implements DBManagement {
    public static final String ANIMAL_TYPE = "non-endangered";

    public Animals(String animalName, int rangerId){
        this.animalName = animalName;
        this.rangerId = rangerId;
        type = ANIMAL_TYPE;
    }
    public void save() {
        try (Connection con = DB.sql2o.beginTransaction()) {
            String sql = "INSERT INTO animals (animalName, rangerId, type)" +
                    "VALUES (:animalName, :rangerId, :type)";
            con.createQuery(sql)
                    .addParameter("animalName", this.animalName)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("type", this.type)
                    .executeUpdate();
            String idQuery = "SELECT lastval()";
            this.animalId = con.createQuery(idQuery).executeScalar(Integer.class);
            con.commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public static List<Animals> all() {
        String sql = "SELECT * FROM animals WHERE type = 'non-endangered';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);
        }
    }
    public static Animals find(int animalId) {
        String sql = "SELECT * FROM animals WHERE animalId = :animalId;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("animalId", animalId)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animals.class);
        }
    }
    public void update() {
        String sql = "UPDATE animals SET animalName = :animalName WHERE animalId = :animalId";
        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("animalName", animalName)
                    .addParameter("animalId", animalId)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }
}
