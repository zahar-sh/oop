package cars.engine;

import cars.fuel.Fuel;
import cars.fuelStorage.FuelStorage;

public abstract class Engine<F extends Fuel> {
    F consumption;

    public Engine(F consumption) {
        this.consumption = consumption;
    }

    public F getConsumption() {
        return consumption;
    }
    public void setConsumption(F consumption) {
        this.consumption = consumption;
    }

    public int work() {
        return work(getFuelStorage().take(getConsumption()));
    }
    protected abstract int work(F fuel);

    public abstract FuelStorage<F> getFuelStorage();
    public abstract void setFuelStorage(FuelStorage<F> storage);
}