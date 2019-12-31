package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class Bar {

    private int id;
    private String date;
    private String drink;
    private int numberOfDrink;
    private int prixUnit;
    int prixTotal = 0;

    public Bar(String drink, int numberOfDrink, int prixUnit, int prixTotal){
        this.drink = drink;
        this.numberOfDrink = numberOfDrink;
        this.prixUnit = prixUnit;
        this.prixTotal = prixTotal;
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

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public int getNumberOfDrink() {
        return numberOfDrink;
    }

    public void setNumberOfDrink(int numberOfDrink) {
        this.numberOfDrink = numberOfDrink;
    }

    public int getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(int prixUnit) {
        this.prixUnit = prixUnit;
    }

    public int getPrixTotal() {
        prixTotal = numberOfDrink * prixUnit;
        return prixTotal;
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO bar(date, drink, numberofdrink, prixunit, prixtotal) VALUES(now(), :drink, :numberOfDrink, :prixUnit, :prixTotal);";
            con.createQuery(sql)
                    .addParameter("drink", this.drink)
                    .addParameter("numberOfDrink", this.numberOfDrink)
                    .addParameter("prixUnit", this.prixUnit)
                    .addParameter("prixTotal", this.prixTotal)
                    .executeUpdate();
        }
    }

    public static List<Bar> all(){
        String sql = "SELECT * FROM bar";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Bar.class);
        }
    }

    public static void deleteById(int id) {
        String sql = "DELETE from bar WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
