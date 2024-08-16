package cars.engine;

import cars.fuel.Electricity;
import cars.fuelStorage.FuelStorage;

public class ElectoEngine extends Engine<Electricity>  {
    private FuelStorage<Electricity> battery;

    public ElectoEngine(Electricity consumption, FuelStorage<Electricity> battery) {
        super(consumption);
        this.battery = battery;
    }

    public FuelStorage<Electricity> getFuelStorage() {
        return battery;
    }
    public void setFuelStorage(FuelStorage<Electricity> storage) {
        this.battery = storage;
    }

    protected int work(Electricity electricity) {
        int u = electricity.getU();
        int power = electricity.takePower();
        System.out.println("ElectricityEngine: " + u + "->" + power);
        return power;
    }

    public String toString() {
        return "ElectoEngine{consumption=" + consumption +
                "\n fuelStorage=" + battery + '}';
    }
}
