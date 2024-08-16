package cars.car;

import cars.Body;
import cars.engine.Engine;
import cars.fuel.Bensine;
import cars.transmission.Transmission;

public class BenzineCar extends Car<Bensine> {
    public BenzineCar(Body body, Engine<Bensine> engine, Transmission transmission) {
        super(body, engine, transmission);
    }

    public String toString() {
        return "BenzineCar{body=" + getBody() +
                "\n engine=" + getEngine() +
                "\n transmission=" + getTransmission() +
                "\n lastEnginePower=" + lastEnginePower +
                '}';
    }
}
