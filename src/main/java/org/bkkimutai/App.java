package org.bkkimutai;

import org.bkkimutai.models.AnimalWithSighting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> payload = new HashMap<>();
            List<AnimalWithSighting> AnimalWithLocation= AnimalWithSighting.getAllAnimalsWithSightings();
            payload.put("AnimalWithLocation", AnimalWithLocation);
            return new ModelAndView(payload, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}