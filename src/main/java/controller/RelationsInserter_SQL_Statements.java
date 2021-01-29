package controller;

import view.RelationsInserterGUI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class containing the methods that are called when a SQL query is triggered in the RelationsInserterGUI
 */
public class RelationsInserter_SQL_Statements
{
    private Connector connector;
    private RelationsInserterGUI gui;
    private WordList wordList;
    private WordParser wordParser;
    private WordMap wordMap;

    private ResultSet ingredient;
    private ResultSet recipeIngredients;
    private ResultSet recipes;

    public RelationsInserter_SQL_Statements(Connector connector)
    {
        this.connector = connector;
        this.wordList = new WordList();
        this.wordParser = new WordParser();
        this.wordMap = new WordMap();
    }

    /**
     * Sets the GUI that this class is going to update
     * @param gui The gui that is used
     */
    public void setGui(RelationsInserterGUI gui)
    {
        this.gui = gui;
    }

    /**
     * Invoked when "Load Recipes" button is pressed in the RelationsInserterGUI
     * Queries the DB for the titles of the recipes in the DB and adds them to the GUI
     */
    public void LoadRecipes()
    {
        ArrayList<String> recipesArray = new ArrayList();

        try
        {
            String query = "SELECT * FROM recipes";
            Statement statement = connector.getConnection().createStatement();
            statement.executeQuery(query);
            recipes = statement.getResultSet();

            String item = "";
            while (recipes.next())
            {
                item = recipes.getString("title");
                recipesArray.add(item);
                //System.out.println(item);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        gui.setRecipes(recipesArray);
    }

    /**
     * Invoked when a recipe is selected in the GUI
     * Queries the DB for the ingredients in the recipe and ads them to the GUI
     * @param item The title of the selected recipe
     */
    public void LoadRecipeIngredients(Object item)
    {
        String titleString = (String) item;
        String ingredientsString = "";
        try
        {
            String query = "SELECT * FROM recipes WHERE title='" + titleString + "';";
            Statement statement = connector.getConnection().createStatement();
            statement.execute(query);

            recipeIngredients = statement.getResultSet();

           while(recipeIngredients.next())
           {
               ingredientsString = recipeIngredients.getString("ingredients").toLowerCase();
           }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //System.out.println(ingredientsString);
        String[] ingredientsStringArray = ingredientsString.split("\\\\");

/*        for (String s : ingredientsStringArray)
        {
            System.out.println(s);
        }*/

        gui.updateRecipeIngredients(ingredientsStringArray);
    }

    /**
     * Queries the DB for the id related to a recipe
     * @param selectedRecipe Title of the selected recipe
     * @return the DB id
     */
    public int getRecipeId(String selectedRecipe)
    {
        try
        {
            String query = "SELECT * FROM recipes WHERE title='" + selectedRecipe + "'";
            Statement statement = connector.getConnection().createStatement();
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

    /**
     * Queries the DB for ingredients that are related to the input ingredient
     * @param result name of ingredient
     * @return resultset from the query
     */
    public ResultSet loadDatabaseIngredient(String result)
    {
        try
        {
            String query = "SELECT * FROM ingredients2 WHERE title LIKE '%" + result + "%'";
            Statement statement = connector.getConnection().createStatement();
            //System.out.println("Got ingredient with query: " + query);
            return statement.executeQuery(query);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes the selected ingredient from the recipe and parses the string to select only the ingredient
     * (removes the amount and the unit if there)
     * @param selectedValue The selected ingredient
     */
    public void parseQuery(String selectedValue)
    {
        if (selectedValue == null)
        {
            return;
        }

        //System.out.println(selectedValue);

        //Splits the string
        String[] array = selectedValue.split(" ");
        String result = "";

        //Checks each part of the string
        for (int i = 0; i < array.length; i++)
        {
            //If the part of the string is a number or a word contained in wordList.txt
            if (wordParser.boolParse(array[i]) || wordList.contains(array[i]))
            {
                //Print the word/Do nothing
                //System.out.println(array[i]);
            }
            else
            {
                //Set result to the desired value (the ingredient)
                result += wordParser.removeChar(array[i], ',') + " ";
            }
        }

        if (result != "")
        {
            //Vet inte vad detta är
            result = result.substring(0, result.length() - 1);
            result = wordMap.get(result);
            //System.out.println("Result: " + result);

            //Call method loadDatabaseIngredient
            ingredient = loadDatabaseIngredient(result);
        }
        LinkedList<String> ingredientsList = new LinkedList<String>();
        try
        {
            while (ingredient.next())
            {
                //System.out.println("Got ingredient: " + ingredient.getString("title"));

                //Creates a string with the desired information and adds it to the list
                String res = "#";
                res += ingredient.getInt("id") + " ";
                res += ingredient.getString("title") + " - ";
                res += ingredient.getFloat("price") + " ";
                res += ingredient.getString("pricetype");
                ingredientsList.add(res);
                //System.out.println("Got ingredient result: " + res);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        String[] ingredientsListArray = new String[ingredientsList.size()];

        for (int i = 0; i < ingredientsListArray.length; i++)
        {
            ingredientsListArray[i] = ingredientsList.get(i);
        }

        //Send the array with ingredients to the GUI
        gui.updateDatabaseIngredients(ingredientsListArray);
    }

    /**
     * Invoked from the GUI
     * Inserts a relation between a recipe and a ingredient in the DB
     * @param recipeId Id of the recipe
     * @param ingredientId Id of the ingredient
     * @param ingredientUnit Unit of the ingredient
     */
    private void sendRelation(int recipeId, String ingredientId, String ingredientUnit)
    {
        try
        {
            String query = "INSERT INTO relations(recipe_id,ingredients_id,units) VALUES (?,?,?)";
            PreparedStatement statement = connector.getConnection().prepareStatement(query);
            statement.setInt(1, recipeId);
            statement.setInt(2, Integer.parseInt(ingredientId));
            statement.setInt(3, Integer.parseInt(ingredientUnit));
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Takes the selected ingredients and parses the string to get the necessary information
     * then calls the sendRealation method
     * @param selectedItem The selected recipe
     * @param array Array of ingredients
     */
    public void sendQuery(Object selectedItem, Object[] array)
    {
        String selectedRecipe = (String) selectedItem;
        String[] selectedIngredients = new String[array.length];

        for (int i = 0; i < array.length; i++)
        {
            selectedIngredients[i] = (String) array[i];
        }

        //System.out.println("Selected recipe: " + selectedRecipe);

        for (String s : selectedIngredients)
        {
            System.out.println("Selected ingredient: " + s);
        }

        int recipeId = getRecipeId(selectedRecipe);

        //System.out.println("Recipe id: " + recipeId);

        for (String s : selectedIngredients)
        {
            String[] ingredientArray = s.split(" ");
            String ingredientId = ingredientArray[0].substring(1, ingredientArray[0].length());
            String ingredientUnit = ingredientArray[ingredientArray.length - 1];
            //System.out.println("Ingredient query: " + recipeId + " - " + ingredientId + " - " + ingredientUnit);
            sendRelation(recipeId, ingredientId, ingredientUnit);
        }
    }
}
