package controller;

import entity.IngredientAmount;
import entity.Product;
import view.CreateRecipeView;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    CreateRecipeView recipeView;
    Connector connector;

    public Controller () {
        recipeView = new CreateRecipeView(this);
        recipeView.setVisible(true);
        connector = new Connector();

    }

    /*
    Inserts every product in a list into the database
     */
    public void populateDB(ArrayList<Product> list) {
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
        System.out.println("Finished poopulating Database");
    }

    /*
    Generates an SQL query string for an insert operation of a product
     */
    public String generateInsertIngredientQuery(Product product){

        String name = product.getProd_name();
        String price = product.getProd_price();
        String unit = product.getUnit();
        String query = "INSERT INTO FoodBankDB.dbo.Livsmedel(l_namn, l_pris, l_enhet) VALUES ('" + name + "'," + price + ",'" + unit + "');";

        return query;
    }


    public void getSearch(){
        recipeView.getListIngModel().clear();
        try {
            String query = "SELECT l_namn FROM FoodBankDB.dbo.Livsmedel where l_namn Like '%" + recipeView.getSearchRep() + "%'";
            Statement st = connector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                recipeView.getListIngModel().addElement(rs.getString(1));
            }

            //System.out.println(st.execute(query));

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /*
    Adds a new recipe into the database
     */
    public void addRecipe(String recipeName, int portions, String description, ArrayList<IngredientAmount> ingList) throws SQLException {

        CallableStatement call = connector.getConnection().prepareCall("{call FoodBankDB.dbo.addRecipe(?, ?, ?, ?)}");
        call.setString(1, recipeName);
        call.setInt(2,portions);
        call.setString(3, description);
        call.registerOutParameter(4, Types.INTEGER);
        call.execute();

        int recipeID = call.getInt(6);
        Statement st = connector.getConnection().createStatement();
        for (int i = 0; i < ingList.size(); i++){
            int ingredientID = ingList.get(i).getIngredientID();
            String amount = ingList.get(i).getAmount();
            String query = "INSERT INTO FoodBankDB.dbo.ReceptIngredienser(l_id, r_id, mängd) VALUES(" + ingredientID + ", " + recipeID + ", " + amount + ");";
            st.executeQuery(query);
        }
    }

    public void addToIngredients() {

    }

    /*
    Adds a new ingredient to the database
     */
    public void addIngredient(String prod_name, int price, String unit) throws SQLException {
        String queryAddIngredient = "Insert into FoodBankDB.dbo.Livsmedel(l_namn, l_pris, l_enhet) values('" + prod_name + "'," + price + ",'" + unit + "');";
        System.out.println(queryAddIngredient); //For tracing process
        Statement st = connector.getConnection().createStatement();
        st.executeUpdate(queryAddIngredient);

    }

    public static void main(String[] args) {
        Controller c = new Controller();
        JsonToObject popper = new JsonToObject();
        ArrayList<Product> list = popper.parseJsonEx("files/coopSort.json");
        c.populateDB(list);
    }
}
