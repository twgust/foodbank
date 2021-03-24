package utils;

import controller.Connector;
import entity.Product;
import utils.JsonToObject;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Run this before using the app for first time to populate the database.
 */
public class DatabasePopulator {

    /**
     * Use before using the app for first time.
      */
    public static void main(String[] args) {
        DatabasePopulator databasePopulator = new DatabasePopulator();
        databasePopulator.populateDatabase();
    }

    /*
        This code can be used to populate the database. Run only once if needed.
        Wait for confirmation message before doing anything else in GUI.
        Other Json file can be used if formatted the same way.
    */
    public void populateDatabase() {
        JsonToObject popper = new JsonToObject();
        ArrayList<Product> list = popper.parseJsonEx("files/coopSort.json");
        populateDB(list);
    }

    /*
    Inserts every product in a list into the database
    Use once if database is empty
     */
    public void populateDB(ArrayList<Product> list) {
        Connector connector = new Connector();
        String query = "";
        try {
            Statement st = connector.getConnection().createStatement();
            for (Product product : list) {
                query = generateInsertIngredientQuery(product);
                st.executeUpdate(query);
            }
            st.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(query);
        }
        System.out.println("Finished populating Database");
    }

    /*
    Generates an SQL query string for an insert operation of a product
     */
    private String generateInsertIngredientQuery(Product product){

        String name = product.getProd_name();
        float price = product.getProd_price();
        String unit = product.getUnit();
        String query = "INSERT INTO FoodBankDB.dbo.Livsmedel(l_namn, l_pris, l_enhet) VALUES ('" + name + "'," + price + ",'" + unit + "');";

        return query;
    }
}
