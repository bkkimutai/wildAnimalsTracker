package org.bkkimutai.models;

import org.bkkimutai.DB.DB;
import org.bkkimutai.DB.DBManagement;
import org.sql2o.Connection;

public class Location implements DBManagement {
    String locationName;
    int locationId;

    public Location(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO locations (locationName) VALUES (:locationName);";
            this.locationId = (int) con.createQuery(sql, true)
                    .addParameter("locationName", this.locationName)
                    .executeUpdate()
                    .getKey();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        }
    }
}
