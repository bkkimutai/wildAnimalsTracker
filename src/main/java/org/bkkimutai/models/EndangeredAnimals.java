package org.bkkimutai.models;

import org.bkkimutai.DB.DB;
import org.bkkimutai.DB.DBManagement;
import org.sql2o.Connection;

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
            String sql = "INSERT INTO animals (name, rangerId, health, age, type) VALUES (:name, :health, :age, :type)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .addParameter("type", this.type)
                    .executeUpdate();
            String idQuery = "SELECT lastval()";
            this.id = con.createQuery(idQuery).executeScalar(Integer.class);
            con.commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
