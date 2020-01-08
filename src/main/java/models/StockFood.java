package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class StockFood {

    private int id;
    private String date;
    private String foodName;
    private int quantity;
    private String pack;
    private int priceUnitFood;
    int totalPrice = 0;

    public StockFood(String foodName, int quantity, String pack, int priceUnitFood, int totalPrice){
        this.foodName = foodName;
        this.quantity = quantity;
        this.pack = pack;
        this.priceUnitFood = priceUnitFood;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public int getPriceUnitFood() {
        return priceUnitFood;
    }

    public void setPriceUnitFood(int priceUnitFood) {
        this.priceUnitFood = priceUnitFood;
    }

    public int getTotalPrice(){
        totalPrice = quantity * priceUnitFood;
        return totalPrice;
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO food(date, foodname, quantity, pack, priceunitfood, totalprice) VALUES(now(), :foodName, :quantity, :pack, :priceUnitFood, :totalPrice);";
            con.createQuery(sql)
                    .addParameter("foodName", this.foodName)
                    .addParameter("quantity", this.quantity)
                    .addParameter("pack", this.pack)
                    .addParameter("priceUnitFood", this.priceUnitFood)
                    .addParameter("totalPrice", this.totalPrice)
                    .executeUpdate();
        }
    }

    public static List<StockFood> all(){
        String sql = "SELECT * FROM food";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(StockFood.class);
        }
    }

    public static void deleteById(int id) {
        String sql = "DELETE from food WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
