package controller;

import view.RecipeInserterGUI;
import view.RelationsInserterGUI;

public class Main {
    public static void main(String[] args) {
        Connector connector = new Connector();
        //Un-comment to start the RecipeInserter
        //RecipeInserterGUI recGui = new RecipeInserterGUI(new RecipeInserter_SQL_Statements(connector));

        //For starting the RealstionsInserter
        //Has to be done in this order for it to work
        RelationsInserter_SQL_Statements stmt = new RelationsInserter_SQL_Statements(connector);
        RelationsInserterGUI relGui = new RelationsInserterGUI(stmt);
        stmt.setGui(relGui);

    }
}
