package controller;

import view.RelationsInserterGUI;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;

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

    public void setGui(RelationsInserterGUI gui)
    {
        this.gui = gui;
    }

    public void LoadRecipes()
    {
        recipes = connector.loadRecipes();
        ArrayList<String> recipesArray = new ArrayList();
        try
        {
            String item = "";
            while (recipes.next())
            {
                item = recipes.getString("title");
                recipesArray.add(item);
                System.out.println(item);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        gui.setRecipes(recipesArray);
    }

    public void LoadRecipeIngredients(Object item)
    {
        String title = (String) item;
        recipeIngredients = connector.loadRecipeIngredients(title);
        String ingredientsString = null;
        try
        {
            recipeIngredients.next();
            ingredientsString = recipeIngredients.getString("ingredients").toLowerCase();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(ingredientsString);
        String[] ingredientsStringArray = ingredientsString.split("\\\\");
        for (String s : ingredientsStringArray)
        {
            System.out.println(s);
        }
        gui.updateRecipeIngredients(ingredientsStringArray);
    }

    public void parseQuery(String selectedValue)
    {
        if (selectedValue == null)
        {
            return;
        }
        System.out.println(selectedValue);
        String[] array = selectedValue.split(" ");
        String result = "";
        String[] resultArray;
        for (int i = 0; i < array.length; i++)
        {
            if (wordParser.boolParse(array[i]) || wordList.contains(array[i]))
            {
                System.out.println(array[i]);
            }
            else
            {
                result += wordParser.removeChar(array[i], ',') + " ";
            }
        }
        if (result != "")
        {
            result = result.substring(0, result.length() - 1);
            result = wordMap.get(result);
            System.out.println("Result: " + result);
            ingredient = connector.loadDatabaseIngredient(result);
        }
        LinkedList<String> ingredientsList = new LinkedList<String>();
        try
        {
            while (ingredient.next())
            {
                System.out.println("Got ingredient: " + ingredient.getString("title"));
                String res = "#";
                res += ingredient.getInt("id") + " ";
                res += ingredient.getString("title") + " - ";
                res += ingredient.getFloat("price") + " ";
                res += ingredient.getString("pricetype");
                ingredientsList.add(res);
                System.out.println("Got ingredient result: " + res);
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
        gui.updateDatabaseIngredients(ingredientsListArray);
    }

    public void sendQuery(Object selectedItem, Object[] array)
    {
        String selectedRecipe = (String) selectedItem;
        String[] selectedIngredients = new String[array.length];
        for (int i = 0; i < array.length; i++)
        {
            selectedIngredients[i] = (String) array[i];
        }
        System.out.println("Selected recipe: " + selectedRecipe);
        for (String s : selectedIngredients)
        {
            System.out.println("Selected ingredient: " + s);
        }
        int recipeId = connector.getRecipeId(selectedRecipe);
        System.out.println("Recipe id: " + recipeId);
        for (String s : selectedIngredients)
        {
            String[] ingredientArray = s.split(" ");
            String ingredientId = ingredientArray[0].substring(1, ingredientArray[0].length());
            String ingredientUnit = ingredientArray[ingredientArray.length - 1];
            System.out.println("Ingredient query: " + recipeId + " - " + ingredientId + " - " + ingredientUnit);
            connector.sendRelation(recipeId, ingredientId, ingredientUnit);
        }
    }
}
