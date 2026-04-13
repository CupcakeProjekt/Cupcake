package app.entities;

public class Cupcake {
    int productID;
    int categoryID;
    Bottom bottom;
    Topping topping;
    int cost;

    public Bottom getBottom() {
        return bottom;
    }

    public void setBottom(Bottom bottom) {
        this.bottom = bottom;
    }

    public int getCost() {
        return bottom.getPrice() + topping.getPrice();
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Cupcake(Bottom bottom, Topping topping) {
        this.bottom = bottom;
        this.topping = topping;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public Bottom getBase() {
        return bottom;
    }

    public void setBase(Bottom bottom) {
        this.bottom = bottom;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public Cupcake(Bottom bottom, int productID, int categoryID, Topping topping) {
        this.bottom = bottom;
        this.productID = productID;
        this.categoryID = categoryID;
        this.topping = topping;
    }
}
