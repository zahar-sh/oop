package cars.car;

import cars.Body;
import cars.engine.Engine;
import cars.fuel.Electricity;
import cars.transmission.Transmission;

public class ElectroCar extends Car<Electricity> {

    public ElectroCar(Body body, Engine<Electricity> engine, Transmission transmission) {
        super(body, engine, transmission);
    }

    public String toString() {
        return "ElectroCar{" +
                "body=" + getBody() +
                "\n engine=" + getEngine() +
                "\n transmission=" + getTransmission() +
                "\n lastEnginePower=" + lastEnginePower +
                '}';
    }
}
