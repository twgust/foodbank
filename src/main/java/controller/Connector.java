package controller;

import java.sql.*;

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

    public Connection getConnection()
    {
        return this.connection;
    }

    public ResultSet loadRecipes()
    {
        try
        {
            String query = "SELECT * FROM recipes";
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet loadRecipeIngredients(String title)
    {
        try
        {
            String query = "SELECT * FROM recipes WHERE title='" + title + "';";
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public int getRecipeId(String selectedRecipe)
    {
        try
        {
            String query = "SELECT * FROM recipes WHERE title='" + selectedRecipe + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet != null)
            {
                resultSet.next();
                return resultSet.getInt("recipe_id");
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public void sendRelation(int recipeId, String ingredientId, String ingredientUnit)
    {
        try
        {
            String query = "INSERT INTO relations(recipe_id,ingredients_id,units) VALUES (?,?,?)";
            try
            {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, recipeId);
                statement.setInt(2, Integer.parseInt(ingredientId));
                statement.setInt(3, Integer.parseInt(ingredientUnit));
                statement.executeUpdate();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet loadDatabaseIngredient(String result)
    {
        try
        {
            String query = "SELECT * FROM ingredients2 WHERE title LIKE '%" + result + "%'";
            Statement statement = connection.createStatement();
            System.out.println("Got ingredient with query: " + query);
            return statement.executeQuery(query);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
