package org.bkkimutai.models;

import org.bkkimutai.DB.DB;
import org.bkkimutai.DB.DBManagement;
import org.sql2o.Connection;

import java.util.List;

public class EndangeredAnimals extends AnimalAbstract implements DBManagement {
    public  String health;
    public String age;
    public static final String ANIMAL_TYPE = "Endangered";

    public EndangeredAnimals(String name, int rangerId,String health, String age){

        if (name.isEmpty() || health.isEmpty() || age.isEmpty()){
            throw new IllegalArgumentException("Please enter all input fields.");
        }
        this.name = name;
        this.rangerId = rangerId;
        this.health = health;
        this.age = age;
        type = ANIMAL_TYPE;

    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public void save() {
        try (Connection con = DB.sql2o.beginTransaction()) {
            String sql = "INSERT INTO animals (name, rangerId, type, health, age, ) VALUES (:name, :rangerId, :type, :health, :age)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("type", this.type)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .executeUpdate();
            String idQuery = "SELECT lastval()";
            this.animalId = con.createQuery(idQuery).executeScalar(Integer.class);
            con.commit();
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
    public static EndangeredAnimals find(int id) {
        String sql = "SELECT * FROM animals WHERE id = :id;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimals.class);
        }
    }

    public void update() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE animals SET name = :name, rangerId= :rangerId, health = :health, age = :age, type= :type WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("health", health)
                    .addParameter("age", age)
                    .addParameter("type", this.type)
                    .addParameter("id", animalId)
                    .executeUpdate();
        }
    }
}
