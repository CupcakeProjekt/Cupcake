package app.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order {
    List<Orderline> orderlineList = new ArrayList<>();
    int orderNumber;
    int userID;
    int cost;

    public Order(List<Orderline> orderlineList, int orderNumber, int userID) {
        this.orderlineList = orderlineList;
        this.orderNumber = orderNumber;
        this.userID = userID;
    }


    public void setOrderlineList(List<Orderline> orderlineList) {
        this.orderlineList = orderlineList;
    }

    public Order(List<Orderline> orderlineList, int orderNumber, int userID, int cost) {
        this.orderlineList = orderlineList;
        this.orderNumber = orderNumber;
        this.userID = userID;
        this.cost = cost;
    }

    public List<Orderline> getOrderlineList() {
        return orderlineList;
    }

    public int getCost() {
        int cost = 0;

        for (Orderline orderline : orderlineList) {
            cost = cost + orderline.getCost();
        }

        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void addOrderlineList(Orderline ol) {
        orderlineList.add(ol);
    }

    public Order(int orderNumber, int userID) {
        this.orderNumber = orderNumber;
        this.userID = userID;
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
