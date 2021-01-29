package controller;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * controller.Connector class, handles the connection to the SQL database and functions used to query data from the DB
 *
 */

public class Connector
{
    private Connection connection;

    // Attempts to establish connection to the SQL server
    public Connector()
    {
        try {
            System.out.println("Connecting to MSSQL database...");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=food;", "user", "123");
            //connection = DriverManager.getConnection("jdbc:mysql://195.178.232.16:3306/aj1757","aj1757","foodbank123");
            System.out.println("Successfully connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                PreparedStatement statement = connection.prepareStatement(query);
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
