package app.entities;

public class Base {
    int bottomID;
    String bottomName;
    String bottomDesc;

    public Base(int bottomID, String bottomName, String bottomDesc) {
        this.bottomID = bottomID;
        this.bottomName = bottomName;
        this.bottomDesc = bottomDesc;
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
