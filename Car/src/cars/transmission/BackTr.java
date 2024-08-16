package cars.transmission;

import cars.chassis.Chassis;

public class BackTr extends Transmission {
    public BackTr(KPP kpp, Chassis chassis) {
        super(kpp, chassis);
    }

    public void apply(int power) {
        power = getKpp().apply(power);

        apply(getChassis().getBL(), power, "BackLeft wheel: ");
        apply(getChassis().getBR(), power, "BackRight wheel: ");
    }

    public String toString() {
        return "BackTransmission{KPP=" + kpp +
                "\n chassis=" + chassis + '}';
    }
}
