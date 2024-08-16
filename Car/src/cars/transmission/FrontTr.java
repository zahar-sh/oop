package cars.transmission;

import cars.chassis.Chassis;

public class FrontTr extends Transmission {
    public FrontTr(KPP kpp, Chassis chassis) {
        super(kpp, chassis);
    }

    public void apply(int power) {
        power = getKpp().apply(power);

        apply(getChassis().getFL(), power, "FrontLeft wheel: ");
        apply(getChassis().getFR(), power, "FrontRight wheel: ");
    }

    public String toString() {
        return "FrontTransmission{KPP=" + kpp +
                "\n chassis=" + chassis + '}';
    }
}
