package cars.fuel;

public interface Fuel {
    int expectedPower();
    void invalidate();

    default int takePower() {
        int power = expectedPower();
        invalidate();
        return power;
    }
}
