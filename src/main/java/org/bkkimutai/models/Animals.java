package org.bkkimutai.models;

import org.bkkimutai.DB.DB;
import org.bkkimutai.DB.DBManagement;
import org.sql2o.Connection;

public class Animals extends AnimalAbstract implements DBManagement {
    public static final String ANIMAL_TYPE = "Non-endangered";

    public Animals(String name, int rangerId){

        if (name.isEmpty()){
            throw new IllegalArgumentException("Please enter an animal name.");
        }
        this.name = name;
        this.rangerId = rangerId;
        type = ANIMAL_TYPE;

    }
    public void save() {
        try (Connection con = DB.sql2o.beginTransaction()) {
            String sql = "INSERT INTO animals (name, rangerId)" +
                    "VALUES (:name, :rangerId)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("rangerId", this.rangerId)
                    .executeUpdate();
            String idQuery = "SELECT lastval()";
            this.id = con.createQuery(idQuery).executeScalar(Integer.class);
            con.commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
