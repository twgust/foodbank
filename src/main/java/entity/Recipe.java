package entity;

public class Recipe {
    private String name;
    private int portions;
    private String description;

    public Recipe(String name, int portions, String description){
        this.name = name;
        this.portions = portions;
        this.description = description;
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
