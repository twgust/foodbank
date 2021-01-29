package controller;

import view.RecipeInserterGUI;
import view.RelationsInserterGUI;

public class Main {
    public static void main(String[] args) {
        Connector connector = new Connector();
        //RecipeInserterGUI recGui = new RecipeInserterGUI(new RecipeInserter_SQL_Statements(connector));
        RelationsInserter_SQL_Statements stmt = new RelationsInserter_SQL_Statements(connector);
        RelationsInserterGUI relGui = new RelationsInserterGUI(stmt);
        stmt.setGui(relGui);

    }
}
