package app.entities;

import java.util.List;

public class Order {
    List<Orderline> orderlineList;
    int orderNumber;
    int userID;

    public Order(List<Orderline> orderlineList, int orderNumber, int userID) {
        this.orderlineList = orderlineList;
        this.orderNumber = orderNumber;
        this.userID = userID;
    }


    public void setOrdrelinjeList(List<Orderline> orderlineList) {
        this.orderlineList = orderlineList;
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
