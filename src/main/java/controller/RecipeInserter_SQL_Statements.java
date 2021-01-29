package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;

public class RecipeInserter_SQL_Statements
{
    private Connector connector;

    public RecipeInserter_SQL_Statements(Connector connector)
    {
        this.connector = connector;
    }

    /**
     * Simple insert statement, function invoked when a recipe is added in the GUI client (RecipeInserterGUI)
     * @param category category column in recipe table
     * @param title title column in title table
     * @param description description column in recipe table
     * @param portions portions column in recipe table
     * @param link link column in recipe table
     * @param imageLink imageLink column in recipe table
     * @param ingredients ingredients column in recipe table
     * @param instructions instructions column in recipe table
     * @return returns true if query is executed without sqlexceptions, else false.
     */
    public boolean query(int category, String title, String description, int portions, String link, String imageLink,
                         String ingredients, String instructions) {
        try {
            String query = "INSERT INTO recipes (category,title,portions,descr,ingredients,instructions,image,link) VALUES (?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement statement = connector.getConnection().prepareStatement(query);
                statement.setInt(1, category);
                statement.setString(2, title);
                statement.setInt(3, portions+2);
                statement.setString(4, description);
                statement.setString(5, ingredients);
                statement.setString(6, instructions);
                statement.setString(7, imageLink);
                statement.setString(8, link);
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
