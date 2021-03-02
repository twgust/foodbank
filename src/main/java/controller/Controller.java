package controller;

import entity.IngredientAmount;
import entity.Product;
import entity.Recipe;
import view.CreateRecipeView;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    private CreateRecipeView recipeView;
    private Connector connector;

    public Controller () {
        recipeView = new CreateRecipeView(this);
        recipeView.setVisible(true);
        connector = new Connector();

    }

    /*
    Inserts every product in a list into the database
    Use once if database is empty
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
    private String generateInsertIngredientQuery(Product product){

        String name = product.getProd_name();
        float price = product.getProd_price();
        String unit = product.getUnit();
        String query = "INSERT INTO FoodBankDB.dbo.Livsmedel(l_namn, l_pris, l_enhet) VALUES ('" + name + "'," + price + ",'" + unit + "');";

        return query;
    }


    public void getSearch(){
        recipeView.getListIngModel().clear();
        ArrayList<Product> prodList = new ArrayList<>();
        try {
            String search = recipeView.getSearchRep();
            if(search == null){
                return;
            }
            String query = "SELECT * FROM FoodBankDB.dbo.Livsmedel where l_namn Like '%" + search + "%'";
            Statement st = connector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                float price = rs.getFloat(3);
                String unit = rs.getString(4);
                prodList.add(new Product(id, name, price, unit));
                recipeView.getListIngModel().addElement(name);
            }
            recipeView.setProdList(prodList);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /*
    Adds a new recipe into the database
     */
    public void addRecipe(String recipeName, int portions, String description, ArrayList<IngredientAmount> ingList) {

        try {
            CallableStatement call = connector.getConnection().prepareCall("{call FoodBankDB.dbo.addRecipe(?, ?, ?, ?)}");
            call.setString(1, recipeName);
            call.setInt(2, portions);
            call.setString(3, description);
            call.registerOutParameter(4, Types.INTEGER);
            call.execute();

            int recipeID = call.getInt(4);
            Statement st = connector.getConnection().createStatement();
            for (int i = 0; i < ingList.size(); i++) {
                int ingredientID = ingList.get(i).getIngredientID();
                float amount = ingList.get(i).getAmount();
                String query = "INSERT INTO FoodBankDB.dbo.ReceptIngredienser(l_id, r_id, mängd) VALUES" + "(" + ingredientID + ", " + recipeID + ", " + amount + ")";
                st.executeUpdate(query);
            }
            System.out.println("Added " + recipeName + " to database");
            call.close();
            st.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void addToIngredients() {

    }

    /*
    Adds a new ingredient to the database
     */
    public void addIngredient(String prod_name, float price, String unit) {
        String queryAddIngredient = "Insert into FoodBankDB.dbo.Livsmedel(l_namn, l_pris, l_enhet) values('" + prod_name + "'," + price + ",'" + unit + "');";
        System.out.println(queryAddIngredient); //For tracing process
        try{
            Statement st = connector.getConnection().createStatement();
            st.executeUpdate(queryAddIngredient);
            st.close();
        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    /*
    Retrieves all recipes from the database and returns them in an ArrayList
     */
    public ArrayList<Recipe> getAllRecipes(){
        String query = "SELECT * FROM FoodBankDB.dbo.Recept";
        ArrayList<Recipe> recList = new ArrayList<>();
        try {
            Statement st = connector.getConnection().createStatement();
            ResultSet res = st.executeQuery(query);

            //Iterates through resultset, creating a new Recipe object of every row and adding it to recList
            while(res.next()){
                int recipeID = res.getInt(1);
                String name = res.getString(2);
                int portions = res.getInt(3);
                String description = res.getString(4);
                recList.add(new Recipe(recipeID, name, portions, description));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return recList;
    }

    /*
    Returns details of a recipe not found in Recept table. Should be Ingredient names, amounts, prices and units.
    The data returned is stored as a HashMap of Product as key, IngredientAmount as value.

    To iterate through map use:
    for(Entry<String, Integer> entry: map.entrySet()) {
        Product prod  = entry.getKey();
        IngredientAmount ing = entry.getValue();
    }
     */
    public HashMap<Product, IngredientAmount> getRecipeIngredients(int recipeID){
        String query = "SELECT * FROM FoodBankDB.dbo.ReceptIngredienser WHERE r_id = " + recipeID;
        HashMap<Product, IngredientAmount> map = new HashMap<>();
        try {

            //Gets all ingredients linked to recipe
            Statement st = connector.getConnection().createStatement();
            ResultSet res = st.executeQuery(query);
            ArrayList<IngredientAmount> iaList = new ArrayList<>();

            //Creates an IngredientAmount object from every row in resultset, saves in iaList
            while(res.next()){
                int ingredientID = res.getInt(1);
                float amount = res.getFloat(3);
                iaList.add(new IngredientAmount(ingredientID, amount));
            }

            //Gets every ingredient from DB table Livsmedel by the ingredientID
            for (int i = 0; i < iaList.size(); i++) {
                String newQuery = "SELECT * FROM FoodBankDB.dbo.Livsmedel WHERE l_id = " + iaList.get(i).getIngredientID();
                ResultSet resultSet = st.executeQuery(newQuery);
                String prodName = resultSet.getString(2);
                float price = resultSet.getFloat(3);
                String unit = resultSet.getString(4);

                //Creates a Product from the resultset, puts both Product linked IngredientAmount in map
                Product product = new Product(prodName, price, unit);
                map.put(product, iaList.get(i));

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return map;
    }

    /*
    Deletes recipe from database with recipeID
     */
    public void deleteRecipe(int recipeID){
        try {
            PreparedStatement ptsmt = connector.getConnection().prepareCall("{call FoodBankDB.dbo.deleteRecipe(?)}");
            ptsmt.setInt(1, recipeID);
            ptsmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //TODO: this one is tricky idk
    public void editRecipe(){

    }

    public static void main(String[] args) {
        Controller c = new Controller();

        /*
        This code can be used to populate the database. Run only once if needed.
        Other Json file can be used if formatted the same way.



        JsonToObject popper = new JsonToObject();
        ArrayList<Product> list = popper.parseJsonEx("files/coopSort.json");
        c.populateDB(list);

         */

    }
}
