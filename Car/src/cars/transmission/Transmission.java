package cars.transmission;

import cars.chassis.Chassis;
import cars.chassis.Wheel;

public abstract class Transmission {
    KPP kpp;
    Chassis chassis;

    public Transmission(KPP kpp, Chassis chassis) {
        this.kpp = kpp;
        this.chassis = chassis;
    }

    public KPP getKpp() {
        return kpp;
    }
    public void setKpp(KPP kpp) {
        this.kpp = kpp;
    }

    public Chassis getChassis() {
        return chassis;
    }
    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }

    public abstract void apply(int power);

    static void apply(Wheel wheel, int power, String msg) {
        if (wheel != null)
            System.out.println(msg + wheel.apply(power));
    }
}