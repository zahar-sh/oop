package cars.transmission;


import cars.Pipe;

import java.util.Arrays;
import java.util.Objects;

public class KPP implements Pipe {
    private int gear;
    private final Gear[] gears;

    public KPP(Gear[] gears) {
        this.gears = Objects.requireNonNull(gears);
    }

    public int getAllGearCount() {
        return gears.length;
    }
    public int getGear() {
        return gear;
    }
    public void setGear(int gear) {
        if (gear < 0 || gear >= gears.length)
            throw new IllegalArgumentException();
        this.gear = gear;
    }
    public void setBack() {
        setGear(gears.length - 1);
    }
    public void setNeutral() {
        setGear(0);
    }

    public int apply(int power) {
        int p = gears[gear].apply(power);
        System.out.println("KPP: " + p);
        return p;
    }

    public String toString() {
        return "KPP{gear=" + gear +
                "\n gearsCount=" + gears.length +
                "\n gears=" + Arrays.toString(gears) + '}';
    }
}
