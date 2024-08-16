package cars.chassis;

public class Chassis {
    private Wheel FL, FR, BL, BR;
    private final int maxBrakeForce;

    public Chassis(int maxBrakeForce,
                   Wheel FL, Wheel FR,
                   Wheel BL, Wheel BR) {
        this.maxBrakeForce = maxBrakeForce;
        this.FL = FL;
        this.FR = FR;
        this.BL = BL;
        this.BR = BR;
    }

    public Wheel getFL() {
        return FL;
    }
    public void setFL(Wheel FL) {
        this.FL = FL;
    }

    public Wheel getFR() {
        return FR;
    }
    public void setFR(Wheel FR) {
        this.FR = FR;
    }

    public Wheel getBL() {
        return BL;
    }
    public void setBL(Wheel BL) {
        this.BL = BL;
    }

    public Wheel getBR() {
        return BR;
    }
    public void setBR(Wheel BR) {
        this.BR = BR;
    }

    public void setAllBrakeForce(int force) {
        if (force > getMaxBrakeForce())
            throw new IllegalArgumentException();
        if (FL != null) FL.setBrakeForce(force);
        if (FR != null) FR.setBrakeForce(force);
        if (BL != null) BL.setBrakeForce(force);
        if (BR != null) BR.setBrakeForce(force);
    }
    public int getMaxBrakeForce() {
        return maxBrakeForce;
    }

    public String toString() {
        return "Chassis{FL=" + FL +
                "\n FR=" + FR +
                "\n BL=" + BL +
                "\n BR=" + BR + '}';
    }
}