package app.entities;

public class Ordrelinje {
    Cupcake cupcake;
    int antal;

    public Cupcake getCupcake() {
        return cupcake;
    }

    public void setCupcake(Cupcake cupcake) {
        this.cupcake = cupcake;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public Ordrelinje(Cupcake cupcake, int antal) {
        this.cupcake = cupcake;
        this.antal = antal;
    }
}
