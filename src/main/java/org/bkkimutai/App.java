package org.bkkimutai;

import org.bkkimutai.models.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> payload = new HashMap<>();
            List<AnimalWithSighting> AnimalWithLocation= AnimalWithSighting.getAllAnimalsWithSightings();
            payload.put("AnimalWithLocation", AnimalWithLocation);
            return new ModelAndView(payload, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //display form to create a new Sighting
        get("/sighting/new", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            Map<String, Object> payload1 = new HashMap<>();
            Map<String, Object> payload2 = new HashMap<>();
            List<AnimalWithSighting>  allAnimals = AnimalWithSighting.getAllAnimalsWithSightings();
            List<Location> locations = Location.all();
            List<Ranger> allRangers = Ranger.all();
            payload.put("allAnimals", allAnimals);
            payload1.put("locations", locations);
            payload2.put("allRangers", allRangers);
            payload.putAll(payload1);
            payload.putAll(payload2);
            return new ModelAndView(payload, "new-sighting.hbs");
        }, new HandlebarsTemplateEngine());

        //process new squad
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
    }
}