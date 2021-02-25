package entity;

public class IngredientAmount {
    private int ingredientID;
    private String amount;

    public IngredientAmount(int ingredientID, String amount){
        this.ingredientID = ingredientID;
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
