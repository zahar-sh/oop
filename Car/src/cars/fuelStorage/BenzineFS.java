package cars.fuelStorage;

import cars.fuel.Bensine;

import java.util.Objects;

public class BenzineFS implements FuelStorage<Bensine> {
    private final Bensine max;
    private Bensine current;

    public BenzineFS(Bensine max, Bensine current) {
        this.max = Objects.requireNonNull(max);
        this.current = Objects.requireNonNull(current);
        if (max.getCapacity() < current.getCapacity())
            throw new IllegalArgumentException();
    }

    public Bensine getMaxLevel() {
        return max;
    }
    public Bensine getFuelLevel() {
        return current;
    }

    public Bensine take(Bensine needed) {
        int newFuel = current.getCapacity() - needed.getCapacity();
        if (newFuel < 0) {
            int ret = current.getCapacity();
            current = new Bensine(0);
            return new Bensine(ret);
        }
        current = new Bensine(newFuel);
        return new Bensine(needed.getCapacity());
    }
    public Bensine apply(Bensine f) {
        int newFuel = current.getCapacity() + f.getCapacity();
        if (newFuel > max.getCapacity()) {
            current = max;
            return new Bensine(newFuel - current.getCapacity());
        }
        current = new Bensine(newFuel);
        return new Bensine(f.getCapacity());
    }

    public String toString() {
        return "BenzineFS{current=" + current +
                "\n max=" + max + '}';
    }
}