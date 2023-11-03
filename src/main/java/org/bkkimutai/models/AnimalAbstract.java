package org.bkkimutai.models;

import org.bkkimutai.DB.DB;
import org.sql2o.Connection;

import java.util.List;

public abstract class AnimalAbstract {
    public String animalName;
    public int rangerId;
    public int animalId;
    public String type;

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getRangerId() {
        return rangerId;
    }

    public void setRangerId(int rangerId) {
        this.rangerId = rangerId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object otherAnimal) {
        if (!(otherAnimal instanceof AnimalAbstract)) {
            return false;
        } else {
            AnimalAbstract newAnimal = (AnimalAbstract) otherAnimal;
            return this.getAnimalName().equals(newAnimal.getAnimalName()) &&
                    this.getType().equals(newAnimal.getType());
        }
    }

}