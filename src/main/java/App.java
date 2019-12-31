import models.Bar;
import models.Cuisine;
import models.Stock;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;


public class App {

    public static void main(String[] args) {

        Map<String, Object> model = new HashMap<String, Object>();
        String layout = "templates/layout.hbs";

        get("/", (request, response) -> {
            model.put("stock", Stock.all());
            return new ModelAndView(model, "welcome.hbs");
        }, new HandlebarsTemplateEngine());

        get("/stock", (request, response) -> {
            model.put("stock", Stock.all());
            return new ModelAndView(model, "stock.hbs");
        }, new HandlebarsTemplateEngine());

        post("/stock", (request, response) -> {
            String name = request.queryParams("name");
            int quantity = Integer.parseInt(request.queryParams("quantity"));
            String pack = request.queryParams("pack");
            int priceUnit = Integer.parseInt(request.queryParams("priceUnit"));
            int totalPrice = quantity * priceUnit;
            Stock newStock = new Stock(name, quantity, pack, priceUnit, totalPrice);
            model.put("name", newStock.getName());
            model.put("quantity", newStock.getQuantity());
            model.put("pack", newStock.getPack());
            model.put("priceUnit", newStock.getPriceUnit());
            model.put("totalPrice", newStock.getTotalPrice());
            newStock.save();
            model.put("stock", Stock.all());
            model.put("StockClass", Stock.class);
            return new ModelAndView(model, "storage.hbs");
        }, new HandlebarsTemplateEngine());

        get("/storage", (request, response) -> {
            model.put("stock", Stock.all());
            return new ModelAndView(model, "storage.hbs");
        }, new HandlebarsTemplateEngine());

        get("/delete/:id", (req, res) -> {
            int delete = Integer.parseInt(req.params("id"));
            Stock.deleteById(delete);
            res.redirect("/storage");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/cuisine", (request, response) -> {
            model.put("cuisine", Cuisine.all());
            return new ModelAndView(model, "cuisine.hbs");
        }, new HandlebarsTemplateEngine());

        post("/cuisine", (request, response) -> {
            String menu = request.queryParams("menu");
            int numberOfMenu = Integer.parseInt(request.queryParams("numberOfMenu"));
            String accomp = request.queryParams("accomp");
            int unitPrice = Integer.parseInt(request.queryParams("unitPrice"));
            int priceTotal = numberOfMenu * unitPrice;
            Cuisine newCuisine = new Cuisine(menu, numberOfMenu, accomp, unitPrice, priceTotal);
            model.put("Menu", newCuisine.getMenu());
            model.put("numberOfMenu", newCuisine.getNumberOfMenu());
            model.put("accomp", newCuisine.getAccomp());
            model.put("unitPrice", newCuisine.getUnitPrice());
            model.put("priceTotal", newCuisine.getPriceTotal());
            newCuisine.save();
            model.put("cuisine", Cuisine.all());
            model.put("CusineClass", Cuisine.class);
            return new ModelAndView(model, "kitchen.hbs");
        }, new HandlebarsTemplateEngine());

        get("/kitchen", (request, response) -> {
            model.put("cuisine", Cuisine.all());
            return new ModelAndView(model, "kitchen.hbs");
        }, new HandlebarsTemplateEngine());

        get("/deleteCuisine/:id", (req, res) -> {
            int delete = Integer.parseInt(req.params("id"));
            Cuisine.deleteById(delete);
            res.redirect("/kitchen");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/boisson", (request, response) -> {
            model.put("bar", Bar.all());
            return new ModelAndView(model, "boisson.hbs");
        }, new HandlebarsTemplateEngine());

        post("/boisson", (request, response) -> {
            String drink = request.queryParams("drink");
            int numberOfDrink = Integer.parseInt(request.queryParams("numberOfDrink"));
            int prixUnit = Integer.parseInt(request.queryParams("prixUnit"));
            int prixTotal = numberOfDrink * prixUnit;
            Bar newBar = new Bar(drink, numberOfDrink, prixUnit, prixTotal);
            model.put("drink", newBar.getDrink());
            model.put("numberOfDrink", newBar.getNumberOfDrink());
            model.put("prixUnit", newBar.getPrixUnit());
            model.put("prixTotal", newBar.getPrixTotal());
            newBar.save();
            model.put("bar", Bar.all());
            model.put("BarClass", Bar.class);
            return new ModelAndView(model, "bar.hbs");
        }, new HandlebarsTemplateEngine());

        get("/bar", (request, response) -> {
            model.put("bar", Bar.all());
            return new ModelAndView(model, "bar.hbs");
        }, new HandlebarsTemplateEngine());

        get("/deleteBar/:id", (req, res) -> {
            int delete = Integer.parseInt(req.params("id"));
            Bar.deleteById(delete);
            res.redirect("/bar");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}