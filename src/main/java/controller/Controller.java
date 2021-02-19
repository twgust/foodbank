package controller;

import view.CreateRecipeView;

import javax.swing.*;
import java.sql.ResultSet;
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
            String query = "SELECT n FROM FoodBankDB.dbo.food where n Like '%" + recipeView.getSearchRep() + "%'";
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

    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
