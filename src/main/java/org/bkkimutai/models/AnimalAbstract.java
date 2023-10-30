package org.bkkimutai.models;

import org.bkkimutai.DB.DB;
import org.sql2o.Connection;

import java.util.Timer;

public abstract class AnimalAbstract {
    public String name;
    public int rangerId;
    public int id;

    public Timer timer;
    public String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRangerId() {
        return rangerId;
    }

    public void setRangerId(int rangerId) {
        this.rangerId = rangerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof AnimalAbstract)) {
            return false;
        } else {
            AnimalAbstract newAnimal = (AnimalAbstract) otherAnimal;
            return this.getName().equals(newAnimal.getName()) &&
                    this.getRangerId() == newAnimal.getRangerId();
        }
    }



}
