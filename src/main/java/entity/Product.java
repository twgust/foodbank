package entity;

public class Product {
    private int id;
    private String prod_name;
    private float prod_price;
    private String unit;

    public Product(String prod_name, float prod_price, String unit) {
        this.prod_name = prod_name;
        this.prod_price = prod_price;
        this.unit = unit;
    }

    public Product(int id, String prod_name, float prod_price, String unit) {
        this.id = id;
        this.prod_name = prod_name;
        this.prod_price = prod_price;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public float getProd_price() {
        return prod_price;
    }

    public String getUnit() {
        return unit;
    }
}
