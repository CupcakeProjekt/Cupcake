package app.entities;

public class Topping {
    int topID;
   String topName;
   String topDesc;
   int price;

    public Topping(int topID, String topName, String topDesc, int price) {
        this.topID = topID;
        this.topName = topName;
        this.topDesc = topDesc;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTopID() {
        return topID;
    }

    public void setTopID(int topID) {
        this.topID = topID;
    }

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }

    public String getTopDesc() {
        return topDesc;
    }

    public void setTopDesc(String topDesc) {
        this.topDesc = topDesc;
    }

    public Topping(int topID, String topName, String topDesc) {
        this.topID = topID;
        this.topName = topName;
        this.topDesc = topDesc;
    }
}
