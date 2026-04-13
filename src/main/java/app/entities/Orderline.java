package app.entities;

public class Orderline {
    Cupcake cupcake;
    int lineID;
    int orderNumber;
    int cost;
    int amount;

    public Cupcake getCupcake() {
        return cupcake;
    }

    public void setCupcake(Cupcake cupcake) {
        this.cupcake = cupcake;
    }

    public int getCost() {
        return cupcake.getCost() * amount;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLineID() {
        return lineID;
    }

    public void setLineID(int lineID) {
        this.lineID = lineID;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Orderline(Cupcake cupcake, int lineID, int orderNumber, int cost, int amount) {
        this.cupcake = cupcake;
        this.lineID = lineID;
        this.orderNumber = orderNumber;
        this.cost = cost;
        this.amount = amount;
    }
}
