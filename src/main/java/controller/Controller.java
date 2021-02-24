package controller;

import view.CreateRecipeView;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    CreateRecipeView recipeView;
    Connector connector;

    public Controller () {
        recipeView = new CreateRecipeView(this);
        recipeView.setVisible(true);
        connector = new Connector();

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

    public void addToIngredients() {

    }

    String queryAddIngredient;
    public void addIngredient(String prod_name, int price, String unit) throws SQLException {
       queryAddIngredient = "Insert into FoodBankDB.dbo.Livsmedel(l_namn, l_pris, l_enhet) values('" + prod_name + "'," + price + ",'" + unit + "');";
       System.out.println(queryAddIngredient);
       Statement st = connector.getConnection().createStatement();
       st.executeUpdate(queryAddIngredient);

    }




    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
