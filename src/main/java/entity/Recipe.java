package entity;

public class Recipe {
    private int recipeID;
    private String name;
    private int portions;
    private String description;

    public Recipe(int recipeID, String name, int portions, String description){
        this.recipeID = recipeID;
        this.name = name;
        this.portions = portions;
        this.description = description;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public String getName() {
        return name;
    }

    public int getPortions() {
        return portions;
    }

    public String getDescription() {
        return description;
    }
}
