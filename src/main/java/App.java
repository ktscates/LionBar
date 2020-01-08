import models.Bar;
import models.Cuisine;
import models.StockDrink;
import models.StockFood;
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
            model.put("food", StockFood.all());
            return new ModelAndView(model, "welcome.hbs");
        }, new HandlebarsTemplateEngine());

        get("/stockFood", (request, response) -> {
            model.put("food", StockFood.all());
            return new ModelAndView(model, "stockFood.hbs");
        }, new HandlebarsTemplateEngine());

        post("/stockFood", (request, response) -> {
            String foodName = request.queryParams("foodName");
            int quantity = Integer.parseInt(request.queryParams("quantity"));
            String pack = request.queryParams("pack");
            int priceUnit = Integer.parseInt(request.queryParams("priceUnitFood"));
            int totalPrice = quantity * priceUnit;
            StockFood newStockFood = new StockFood(foodName, quantity, pack, priceUnit, totalPrice);
            model.put("foodName", newStockFood.getFoodName());
            model.put("quantity", newStockFood.getQuantity());
            model.put("pack", newStockFood.getPack());
            model.put("priceUnitFood", newStockFood.getPriceUnitFood());
            model.put("totalPrice", newStockFood.getTotalPrice());
            newStockFood.save();
            model.put("food", StockFood.all());
            model.put("StockClass", StockFood.class);
            return new ModelAndView(model, "storageFood.hbs");
        }, new HandlebarsTemplateEngine());

        get("/storageFood", (request, response) -> {
            model.put("food", StockFood.all());
            return new ModelAndView(model, "storageFood.hbs");
        }, new HandlebarsTemplateEngine());

        get("/delete/:id", (req, res) -> {
            int delete = Integer.parseInt(req.params("id"));
            StockFood.deleteById(delete);
            res.redirect("/storageFood");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/stockDrink", (request, response) -> {
            model.put("drink", StockFood.all());
            return new ModelAndView(model, "stockDrink.hbs");
        }, new HandlebarsTemplateEngine());

        post("/stockDrink", (request, response) -> {
            String drinkName = request.queryParams("drinkName");
            int quantity = Integer.parseInt(request.queryParams("quantity"));
            String packDrink = request.queryParams("packDrink");
            int priceUnitDrink = Integer.parseInt(request.queryParams("priceUnitDrink"));
            int totalPriceDrink = quantity * priceUnitDrink;
            StockDrink newStockDrink = new StockDrink(drinkName, quantity, packDrink, priceUnitDrink, totalPriceDrink);
            model.put("drinkName", newStockDrink.getDrinkName());
            model.put("quantity", newStockDrink.getQuantity());
            model.put("packDrink", newStockDrink.getPackDrink());
            model.put("priceUnitDrink", newStockDrink.getPriceUnitDrink());
            model.put("totalPriceDrink", newStockDrink.getTotalPriceDrink());
            newStockDrink.save();
            model.put("drink", StockDrink.all());
            model.put("StockDrinkClass", StockDrink.class);
            return new ModelAndView(model, "storageDrink.hbs");
        }, new HandlebarsTemplateEngine());

        get("/storageDrink", (request, response) -> {
            model.put("drink", StockDrink.all());
            return new ModelAndView(model, "storageDrink.hbs");
        }, new HandlebarsTemplateEngine());

        get("/deleteDrink/:id", (req, res) -> {
            int delete = Integer.parseInt(req.params("id"));
            StockDrink.deleteById(delete);
            res.redirect("/storageDrink");
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