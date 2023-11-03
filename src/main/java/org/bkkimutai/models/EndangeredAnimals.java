package org.bkkimutai.models;

import org.bkkimutai.DB.DB;
import org.bkkimutai.DB.DBManagement;
import org.sql2o.Connection;

import java.util.List;

public class EndangeredAnimals extends AnimalAbstract implements DBManagement {
    public  String health;
    public String age;
    public static final String ANIMAL_TYPE = "Endangered";

    public EndangeredAnimals(String animalName, int rangerId,String health, String age){
        this.animalName = animalName;
        this.rangerId = rangerId;
        this.health = health;
        this.age = age;
        type = ANIMAL_TYPE;
    }
    public void setHealth(String health) {
        this.health = health;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }


    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (animalName, rangerId, type, health, age) VALUES (:animalName, :rangerId, :type, :health, :age)";
            this.animalId = (int) con.createQuery(sql, true)
                    .addParameter("animalName", this.animalName)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("type", this.type)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .executeUpdate()
                    .getKey();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static List<EndangeredAnimals> all() {
        String sql = "SELECT * FROM animals WHERE type = 'Endangered';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimals.class);
        }
    }
    public static EndangeredAnimals find(int animalId) {
        String sql = "SELECT * FROM animals WHERE animalId = :animalId;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("animalId", animalId)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimals.class);
        }
    }

    public void update() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE animals SET animalName = :animalName, rangerId= :rangerId, health = :health, age = :age, type= :type WHERE animalId = :animalId";
            con.createQuery(sql)
                    .addParameter("animalName", animalName)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("health", health)
                    .addParameter("age", age)
                    .addParameter("type", this.type)
                    .addParameter("animalId", animalId)
                    .executeUpdate();
        }
    }
}
