package cars.transmission;

import cars.Pipe;

public class Gear implements Pipe {
    public static final Gear NEUTRAL = new Gear(0);
    private final float val;

    public Gear(float val) {
        this.val = val;
    }

    public int apply(int power) {
        return (int) (power * val);
    }
    public String toString() {
        return "Gear{val=" + val + '}';
    }
}