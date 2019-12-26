package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class Stock {

    private int id;
    private String date;
    private String name;
    private int quantity;
    private String pack;
    private int priceUnit;
    int totalPrice = 0;

    public Stock(String name, int quantity, String pack, int priceUnit, int totalPrice){
        this.name = name;
        this.quantity = quantity;
        this.pack = pack;
        this.priceUnit = priceUnit;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(int priceUnit) {
        this.priceUnit = priceUnit;
    }

    public int getTotalPrice(){
        totalPrice = quantity * priceUnit;
        return totalPrice;
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO stock(date, name, quantity, pack, priceunit, totalprice) VALUES(now(), :name, :quantity, :pack, :priceUnit, :totalPrice);";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("quantity", this.quantity)
                    .addParameter("pack", this.pack)
                    .addParameter("priceUnit", this.priceUnit)
                    .addParameter("totalPrice", this.totalPrice)
                    .executeUpdate();
        }
    }

    public static List<Stock> all(){
        String sql = "SELECT * FROM stock";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Stock.class);
        }
    }

    public static void deleteById(int id) {
        String sql = "DELETE from stock WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
