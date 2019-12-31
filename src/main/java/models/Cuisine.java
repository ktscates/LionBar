package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class Cuisine {

    private int id;
    private String date;
    private String menu;
    private int numberOfMenu;
    private String accomp;
    private int unitPrice;
    int priceTotal = 0;

    public Cuisine(String menu, int numberOfMenu, String accomp, int unitPrice, int priceTotal){
        this.menu = menu;
        this.numberOfMenu = numberOfMenu;
        this.accomp = accomp;
        this.unitPrice = unitPrice;
        this.priceTotal = priceTotal;
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getNumberOfMenu() {
        return numberOfMenu;
    }

    public void setNumberOfMenu(int numberOfMenu) {
        this.numberOfMenu = numberOfMenu;
    }

    public String getAccomp() {
        return accomp;
    }

    public void setAccomp(String accomp) {
        this.accomp = accomp;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getPriceTotal() {
       priceTotal = numberOfMenu * unitPrice;
        return priceTotal;
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO cuisine(date, menu, numberofmenu, accomp, unitprice, pricetotal) VALUES(now(), :menu, :numberOfMenu, :accomp, :unitPrice, :priceTotal);";
            con.createQuery(sql)
                    .addParameter("menu", this.menu)
                    .addParameter("numberOfMenu", this.numberOfMenu)
                    .addParameter("accomp", this.accomp)
                    .addParameter("unitPrice", this.unitPrice)
                    .addParameter("priceTotal", this.priceTotal)
                    .executeUpdate();
        }
    }

    public static List<Cuisine> all(){
        String sql = "SELECT * FROM cuisine";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Cuisine.class);
        }
    }

    public static void deleteById(int id) {
        String sql = "DELETE from cuisine WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
