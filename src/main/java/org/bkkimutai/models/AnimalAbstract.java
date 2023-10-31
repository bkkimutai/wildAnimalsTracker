package org.bkkimutai.models;

import java.util.Timer;

public abstract class AnimalAbstract {
    public String name;
    public int rangerId;
    public int animalId;
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

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
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
