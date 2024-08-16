package cars.car;

import cars.Body;
import cars.chassis.Chassis;
import cars.engine.Engine;
import cars.fuel.Fuel;
import cars.fuelStorage.FuelStorage;
import cars.transmission.Transmission;

public class Car<F extends Fuel> {
    private Body body;
    private Engine<F> engine;
    private Transmission transmission;

    int lastEnginePower;

    public Car(Body body, Engine<F> engine, Transmission transmission) {
        this.body = body;
        this.engine = engine;
        this.transmission = transmission;
    }

    public Body getBody() {
        return body;
    }
    public void setBody(Body body) {
        this.body = body;
    }

    public Engine<F> getEngine() {
        return engine;
    }
    public void setEngine(Engine<F> engine) {
        this.engine = engine;
    }

    public FuelStorage<F> getFuelStorage() {
        return getEngine().getFuelStorage();
    }
    public void setFuelStorage(FuelStorage<F> fuelStorage) {
        getEngine().setFuelStorage(fuelStorage);
    }

    public Transmission getTransmission() {
        return transmission;
    }
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Chassis getChassis() {
        return getTransmission().getChassis();
    }
    public void setChassis(Chassis chassis) {
        getTransmission().setChassis(chassis);
    }

    public void work() {
        getTransmission().apply(lastEnginePower = getEngine().work());
    }
    public int getGearCount() {
        return getTransmission().getKpp().getAllGearCount();
    }
    public void setGear(int gear) {
        getTransmission().getKpp().setGear(gear);
    }
    public int getMaxBrakeForce() {
        return getTransmission().getChassis().getMaxBrakeForce();
    }
    public void setBrakeForce(int force) {
        getTransmission().getChassis().setAllBrakeForce(force);
    }
}