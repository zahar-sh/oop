package cars.fuelStorage;

import cars.fuel.Fuel;

public interface FuelStorage<T extends Fuel> {
    T getMaxLevel();
    T getFuelLevel();

    T take(T needed);
    T apply(T f);
}