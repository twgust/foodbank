package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RelationPriceInserter_SQL_Statements {
    private Connector connector;

    public RelationPriceInserter_SQL_Statements(Connector connector){
        this.connector = connector;
    }
    public ResultSet loadRelations(int recipeId) {
        try {
            String query = "SELECT * FROM relationthas WHERE recipe_id=" + recipeId;
            Statement statement = connector.getConnection().createStatement();
            return statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet loadAllRelations() {
        try {
            String query = "SELECT * FROM relations";
            Statement statement = connector.getConnection().createStatement();
            return statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet loadIngredient(int id) {
        try {
            String query = "SELECT * FROM ingredients2 WHERE id=" + id;
            Statement statement = connector.getConnection().createStatement();
            return statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertRelationPrice(int id, float price) {
        try {
            String query = "UPDATE relations SET price=" + price + " WHERE relation_id=" + id;
            Statement statement = connector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
