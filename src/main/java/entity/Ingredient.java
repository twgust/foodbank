package entity;

public class Ingredient {
    private Product product;
    private IngredientAmount ingredientAmount;

    public Ingredient(Product product, IngredientAmount ingredientAmount) {
        this.product = product;
        this.ingredientAmount = ingredientAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public IngredientAmount getIngredientAmount() {
        return ingredientAmount;
    }

    public void setIngredientAmount(IngredientAmount ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    @Override
    public String toString() {
        return ingredientAmount.getAmount() + " " +
                product.getUnit() + " " +
                product.getProd_name();
    }
}
