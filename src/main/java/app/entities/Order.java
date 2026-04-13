package app.entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
    List<Orderline> orderlineList = new ArrayList<>();
    int orderNumber;
    User user;
    int cost;

    public Order(List<Orderline> orderlineList, int orderNumber, User user) {
        this.orderlineList = orderlineList;
        this.orderNumber = orderNumber;
        this.user = user;
    }


    public void setOrderlineList(List<Orderline> orderlineList) {
        this.orderlineList = orderlineList;
    }

    public Order(List<Orderline> orderlineList, int orderNumber, User user, int cost) {
        this.orderlineList = orderlineList;
        this.orderNumber = orderNumber;
        this.user = user;
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

    public Order(int orderNumber, User user) {
        this.orderNumber = orderNumber;
        this.user = user;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
