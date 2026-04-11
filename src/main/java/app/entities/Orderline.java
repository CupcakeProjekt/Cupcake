package app.entities;

public class Orderline {
    Cupcake cupcake;
    int amount;

    public Cupcake getCupcake() {
        return cupcake;
    }

    public void setCupcake(Cupcake cupcake) {
        this.cupcake = cupcake;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Orderline(Cupcake cupcake, int amount) {
        this.cupcake = cupcake;
        this.amount = amount;
    }
}
