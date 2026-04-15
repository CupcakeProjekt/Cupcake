package app.entities;

public class Topping {
    int topID;
   String topName;
   int price;

    public Topping(int topID, String topName, int price) {
        this.topID = topID;
        this.topName = topName;

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


    public Topping(int topID, String topName) {
        this.topID = topID;
        this.topName = topName;

    }
}
