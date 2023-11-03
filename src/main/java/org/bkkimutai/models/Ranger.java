package org.bkkimutai.models;

import org.bkkimutai.DB.DB;
import org.bkkimutai.DB.DBManagement;
import org.sql2o.Connection;

import java.util.List;

public class Ranger implements DBManagement {
    String rangerName;
    String email;
    String badgeNumber;
    int rangerId;

    public Ranger(String rangerName, String email, String badgeNumber){
        this.rangerName = rangerName;
        this.email = email;
        this.badgeNumber = badgeNumber;
    }

    public void save() {
        try (Connection con = DB.sql2o.beginTransaction()) {
            String sql = "INSERT INTO rangers (rangerName, email, badgeNumber) VALUES (:rangerName, :email, :badgeNumber);";
            con.createQuery(sql)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("email", this.email)
                    .addParameter("badgeNumber", this.badgeNumber)
                    .executeUpdate();
            String idQuery = "SELECT lastval()";
            this.rangerId = con.createQuery(idQuery).executeScalar(Integer.class);
            con.commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public static List<Ranger> all() {
        String sql = "SELECT * FROM rangers;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Ranger.class);
        }
    }
    public static Ranger find(int rangerId) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM rangers where rangerId = :rangerId";
            return con.createQuery(sql)
                    .addParameter("rangerId", rangerId)
                    .executeAndFetchFirst(Ranger.class);
        }
    }
    public String getRangerName() {
        return rangerName;
    }

    public void setRangerName(String rangerName) {
        this.rangerName = rangerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public int getRangerId() {
        return rangerId;
    }

    public void setRangerId(int rangerId) {
        this.rangerId = rangerId;
    }
}
