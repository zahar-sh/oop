package cars.fuelStorage;


import cars.fuel.Electricity;

import java.util.Objects;

public class Battery implements FuelStorage<Electricity> {
    private final Electricity max;
    private Electricity current;

    public Battery(Electricity max, Electricity current) {
        this.max = Objects.requireNonNull(max);
        this.current = Objects.requireNonNull(current);
        if (max.getU() < current.getU())
            throw new IllegalArgumentException();
    }

    public Electricity getMaxLevel() {
        return max;
    }
    public Electricity getFuelLevel() {
        return current;
    }

    public Electricity take(Electricity needed) {
        int newFuel = current.getU() - needed.getU();
        if (newFuel < 0) {
            int ret = current.getU();
            current = new Electricity(0);
            return new Electricity(ret);
        }
        current = new Electricity(newFuel);
        return new Electricity(needed.getU());
    }
    public Electricity apply(Electricity electricity) {
        int newFuel = current.getU() + electricity.getU();
        if (newFuel > max.getU()) {
            current = max;
            return new Electricity(newFuel - current.getU());
        }
        current = new Electricity(newFuel);
        return new Electricity(electricity.getU());
    }

    public String toString() {
        return "Battery{current=" + current +
                "\n max=" + max + '}';
    }
}
