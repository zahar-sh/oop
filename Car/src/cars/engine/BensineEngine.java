package cars.engine;

import cars.fuel.Bensine;
import cars.fuelStorage.FuelStorage;

public class BensineEngine extends Engine<Bensine> {
    private FuelStorage<Bensine> fuelStorage;

    public BensineEngine(Bensine consumption, FuelStorage<Bensine> fuelStorage) {
        super(consumption);
        this.fuelStorage = fuelStorage;
    }


    public FuelStorage<Bensine> getFuelStorage() {
        return fuelStorage;
    }
    public void setFuelStorage(FuelStorage<Bensine> storage) {
        this.fuelStorage = storage;
    }

    protected int work(Bensine fuel) {
        int cap = fuel.getCapacity();
        int power = fuel.takePower();
        System.out.println("DieselEngine: " + cap + "->" + power);
        return power;
    }

    public String toString() {
        return "DieselEngine{consumption=" + consumption +
                "\n fuelStorage=" + fuelStorage + '}';
    }
}