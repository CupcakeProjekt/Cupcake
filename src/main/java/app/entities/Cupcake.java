package app.entities;

public class Cupcake {
    int productID;
    int categoryID;
    Base base;
    Topping topping;

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

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public Cupcake(Base base, int productID, int categoryID, Topping topping) {
        this.base = base;
        this.productID = productID;
        this.categoryID = categoryID;
        this.topping = topping;
    }
}
