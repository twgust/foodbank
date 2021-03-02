package entity;

public class IngredientAmount {
    private int ingredientID;
    private float amount;

    public IngredientAmount(int ingredientID, float amount){
        this.ingredientID = ingredientID;
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
