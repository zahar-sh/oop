package cars.fuel;

public class Electricity implements Fuel {
    private int u;

    public Electricity(int u) {
        if (u < 0)
            throw new IllegalArgumentException();
        this.u = u;
    }

    public int getU() {
        return u;
    }
    public int getI(int r) {
        return u / r;
    }
    public int getPower(int r) {
        return u * u / r;
    }

    public int getI() {
        return getI(1);
    }
    public int expectedPower() {
        return getPower(1);
    }
    public void invalidate() {
        u = 0;
    }

    public String toString() {
        return "Electricity{U=" + u + "V}";
    }
}