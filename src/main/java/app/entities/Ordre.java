package app.entities;

import java.util.List;

public class Ordre {
    List<Ordrelinje> ordrelinjeList;
    int orderNumber;
    int userID;

    public Ordre( List<Ordrelinje> ordrelinjeList, int orderNumber, int userID) {
        this.ordrelinjeList = ordrelinjeList;
        this.orderNumber = orderNumber;
        this.userID = userID;
    }


    public void setOrdrelinjeList(List<Ordrelinje> ordrelinjeList) {
        this.ordrelinjeList = ordrelinjeList;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
