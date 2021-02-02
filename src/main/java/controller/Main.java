package controller;

import view.RecipeInserterGUI;
import view.RelationsInserterGUI;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connector connector = new Connector();
        //Un-comment to start the RecipeInserter
        RecipeInserterGUI recGui = new RecipeInserterGUI(new RecipeInserter_SQL_Statements(connector));

        //For starting the RealstionsInserter
        //Has to be done in this order for it to work
        RelationsInserter_SQL_Statements stmt = new RelationsInserter_SQL_Statements(connector);
        RelationsInserterGUI relGui = new RelationsInserterGUI(stmt);

        //this is really weird, changed functions from private to public
        RelationPriceInserter_SQL_Statements relPriceSql = new RelationPriceInserter_SQL_Statements(connector);
        RelationPriceInserter relPrice = new RelationPriceInserter(relPriceSql);
        relPrice.loadRelations();
        relPrice.insertPrices();
        // --------------------

        stmt.setGui(relGui);

    }
}
