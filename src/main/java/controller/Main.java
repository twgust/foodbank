package controller;

import view.RecipeInserterGUI;
import view.RelationsInserterGUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main
{
    private void runInTerminal()
    {
        Connector connector = new Connector();

        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("\n" + "Choose program to run:" + "\n" +
                    "1: RecipeInserter" + "\n" +
                    "2: RelationsInserter" + "\n" +
                    "3: PriceInserter (runs without GUI)");

            String s = reader.readLine();

            switch (s)
            {
                case "1":
                    RecipeInserterGUI recGui = new RecipeInserterGUI(new RecipeInserter_SQL_Statements(connector));
                    break;
                case "2":
                    RelationsInserter_SQL_Statements stmt = new RelationsInserter_SQL_Statements(connector);
                    RelationsInserterGUI relGui = new RelationsInserterGUI(stmt);
                    stmt.setGui(relGui);
                    break;
                case "3":
                    RelationPriceInserter_SQL_Statements relPriceSql = new RelationPriceInserter_SQL_Statements(connector);
                    RelationPriceInserter relPrice = new RelationPriceInserter(relPriceSql);
                    relPrice.loadRelations();
                    relPrice.insertPrices();
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println(" Main - Run in terminal: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException
    {
        Main prog = new Main();
        prog.runInTerminal();
    }
}
