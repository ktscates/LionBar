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

        get("/stocks", (request, response) -> {
            model.put("stock", Stock.all());
            return new ModelAndView(model, "stocks.hbs");
        }, new HandlebarsTemplateEngine());

        post("/stock", (request, response) -> {
            String name= request.queryParams("name");
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
            return new ModelAndView(model, "stocks.hbs");
        }, new HandlebarsTemplateEngine());

        get("/delete/:id", (req, res) -> {
            int delete = Integer.parseInt(req.params("id"));
            Stock.deleteById(delete);
            res.redirect("/stock");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}