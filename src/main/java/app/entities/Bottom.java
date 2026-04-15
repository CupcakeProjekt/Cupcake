package app.entities;

public class Bottom {
    int bottomID;
    String bottomName;
    String bottomDesc;
    int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Bottom(int bottomID, String bottomName, int price) {
        this.bottomID = bottomID;
        this.bottomName = bottomName;
        this.price = price;
    }

    public Bottom(int bottomID, String bottomName) {
        this.bottomID = bottomID;
        this.bottomName = bottomName;
    }

    public int getBottomID() {
        return bottomID;
    }

    public String getBottomName() {
        return bottomName;
    }

    public String getBottomDesc() {
        return bottomDesc;
    }

    public void setBottomID(int bottomID) {
        this.bottomID = bottomID;
    }

    public void setBottomName(String bottomName) {
        this.bottomName = bottomName;
    }

    public void setBottomDesc(String bottomDesc) {
        this.bottomDesc = bottomDesc;
    }
}
