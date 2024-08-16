package cars.chassis;

import cars.Pipe;

public class Wheel implements Pipe {
    private int brakeForce = 0;

    public int getBrakeForce() {
        return brakeForce;
    }
    public void setBrakeForce(int brakeForce) {
        if (brakeForce < 0)
            throw new IllegalArgumentException();
        this.brakeForce = brakeForce;
    }

    public int apply(int power) {
        return power == 0 ? 0 : (power < 0 ?
                Math.min(power + getBrakeForce(), 0) :
                Math.max(power - getBrakeForce(), 0));
    }

    public String toString() {
        return "Wheel{brakeForce=" + brakeForce + '}';
    }
}