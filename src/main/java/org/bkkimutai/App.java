package org.bkkimutai;
import org.bkkimutai.DAO.SightingDao;
import org.bkkimutai.models.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        get("/", (req, res) -> {
            Map<String, Object> payload = new HashMap<>();
            List<EndangeredAnimals> allAnimals= EndangeredAnimals.all();
            payload.put("AnimalWithLocation", allAnimals);
            return new ModelAndView(payload, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //display form to create a new Sighting
        get("/sighting/new", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            Map<String, Object> payload1 = new HashMap<>();
            Map<String, Object> payload2 = new HashMap<>();
            List<Animals>  allAnimals = Animals.all();
            List<Location> locations = Location.all();
            List<Ranger> allRangers = Ranger.all();
            payload.put("allAnimals", allAnimals);
            payload1.put("locations", locations);
            payload2.put("allRangers", allRangers);
            payload.putAll(payload1);
            payload.putAll(payload2);
            return new ModelAndView(payload, "new-sighting.hbs");
        }, new HandlebarsTemplateEngine());

        //process new Sighting
        post("/Sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int animalId = Integer.parseInt(request.queryParams("animalId"));
            int locationId = Integer.parseInt(request.queryParams("locationId"));
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
            Sightings newSighting = new Sightings(animalId, locationId, rangerId);
            SightingDao.addSightings(newSighting);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/animal/new", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            List<Ranger> allRangers = Ranger.all();
            payload.put("allRangers", allRangers);
            return new ModelAndView(payload, "new-animal.hbs");
        }, new HandlebarsTemplateEngine());

        //process new Animal
        post("/animals", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
            String animalName = request.queryParams("animalName");
            Animals newAnimal = new Animals(animalName,rangerId);
            SightingDao.addAnimal(newAnimal);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/endangered-animal/new", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            List<Ranger> allRangers = Ranger.all();
            payload.put("allRangers", allRangers);
            return new ModelAndView(payload, "endangered-animal.hbs");
        }, new HandlebarsTemplateEngine());

        //process new Animal
        post("/endangered-animals", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
            String animalName = request.queryParams("animalName");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            EndangeredAnimals newEndangeredAnimal = new EndangeredAnimals(animalName,rangerId, health, age);
            SightingDao.addEndangeredAnimal(newEndangeredAnimal);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //display a single Animal from a Sighting
        get("/animals/:animalId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Map<String, Object> model1 = new HashMap<>();
            int animalId = Integer.parseInt(request.params("animalId"));
            EndangeredAnimals foundAnimal = EndangeredAnimals.find(animalId);
            model.put("animal", foundAnimal);
            List<SightingWithLocation> foundSighting = SightingWithLocation.findSightingWithLocation(animalId);
            model1.put("sighting", foundSighting);
            model.putAll(model1);
            return new ModelAndView(model, "animal-details.hbs");
        }, new HandlebarsTemplateEngine());

        get("/location/:locationId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Map<String, Object> model1 = new HashMap<>();
            int locationToFindId = Integer.parseInt(request.params("locationId"));
            List<SightingWithLocation> foundlocation = SightingWithLocation.getAnimalsByLocation(locationToFindId);
            model.put("locations", foundlocation);
            Location newLocation = Location.find(locationToFindId);
            model1.put("location", newLocation);
            model.putAll(model1);
            return new ModelAndView(model, "location-details.hbs");
        }, new HandlebarsTemplateEngine());

        get("/ranger/:rangerId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Map<String, Object> model1 = new HashMap<>();
            int rangerToFindId = Integer.parseInt(request.params("rangerId"));
            List<SightingWithLocation> foundRanger = SightingWithLocation.findSightingWithRanger(rangerToFindId);
            model.put("rangers", foundRanger);
            Ranger newRanger = Ranger.find(rangerToFindId);
            model1.put("ranger", newRanger);
            model.putAll(model1);
            return new ModelAndView(model, "ranger-details.hbs");
        }, new HandlebarsTemplateEngine());
    }
}