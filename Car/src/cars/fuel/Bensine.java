package cars.fuel;

public class Bensine implements Fuel {
    private int capacity;

    public Bensine(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException();
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
    public int expectedPower() {
        return capacity * 20;
    }
    public void invalidate() {
        capacity = 0;
    }

    public String toString() {
        return "Bensine{capacity=" + capacity + "ml}";
    }
}