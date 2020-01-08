package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class StockDrink {

    private int id;
    private String date;
    private String drinkName;
    private int quantity;
    private String packDrink;
    private int priceUnitDrink;
    int totalPriceDrink = 0;

    public StockDrink(String drinkName, int quantity, String packDrink, int priceUnitDrink, int totalPriceDrink){
        this.drinkName = drinkName;
        this.quantity = quantity;
        this.packDrink = packDrink;
        this.priceUnitDrink = priceUnitDrink;
        this.totalPriceDrink = totalPriceDrink;
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

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPackDrink() {
        return packDrink;
    }

    public void setPackDrink(String packDrink) {
        this.packDrink = packDrink;
    }

    public int getPriceUnitDrink() {
        return priceUnitDrink;
    }

    public void setPriceUnitDrink(int priceUnitDrink) {
        this.priceUnitDrink = priceUnitDrink;
    }

    public int getTotalPriceDrink(){
        totalPriceDrink = quantity * priceUnitDrink;
        return totalPriceDrink;
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO drink(date, drinkname, quantity, packdrink, priceunitdrink, totalpricedrink) VALUES(now(), :drinkName, :quantity, :packDrink, :priceUnitDrink, :totalPriceDrink);";
            con.createQuery(sql)
                    .addParameter("drinkName", this.drinkName)
                    .addParameter("quantity", this.quantity)
                    .addParameter("packDrink", this.packDrink)
                    .addParameter("priceUnitDrink", this.priceUnitDrink)
                    .addParameter("totalPriceDrink", this.totalPriceDrink)
                    .executeUpdate();
        }
    }

    public static List<StockDrink> all(){
        String sql = "SELECT * FROM drink";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(StockDrink.class);
        }
    }

    public static void deleteById(int id) {
        String sql = "DELETE from drink WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}

