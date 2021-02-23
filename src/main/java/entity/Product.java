package entity;

public class Product {
    private String prod_name;
    private String prod_price;
    private String unit;

    public Product(String prod_name, String prod_price, String unit) {
        this.prod_name = prod_name;
        this.prod_price = prod_price;
        this.unit = unit;
    }

    public String getProd_name() {
        return prod_name;
    }

    public String getProd_price() {
        return prod_price;
    }

    public String getUnit() {
        return unit;
    }
}
