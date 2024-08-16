package cars.transmission;


import cars.chassis.Chassis;

public class FullTr extends BackTr {
    public FullTr(KPP kpp, Chassis chassis) {
        super(kpp, chassis);
    }

    public void apply(int power) {
        power = getKpp().apply(power);

        apply(getChassis().getFL(), power, "FrontLeft wheel: ");
        apply(getChassis().getFR(), power, "FrontRight wheel: ");
        apply(getChassis().getBL(), power, "BackLeft wheel: ");
        apply(getChassis().getBR(), power, "BackRight wheel: ");
    }

    public String toString() {
        return "FullTransmission{KPP=" + kpp +
                "\n chassis=" + chassis + '}';
    }
}