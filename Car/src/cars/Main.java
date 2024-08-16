package cars;

import cars.car.Car;
import cars.fuel.Fuel;

public class Main {
    public static void main(String[] args) {
        test(CarFactory.createElectroCar1());
        test(CarFactory.createElectroCar2());
        test(CarFactory.createElectroCar3());

        test(CarFactory.createBenzineCar1());
        test(CarFactory.createBenzineCar2());
        test(CarFactory.createBenzineCar3());
    }

    private static <F extends Fuel> void test(Car<F> car) {
        car.setGear(3);
        car.setBrakeForce(10);
        System.out.println(car.toString());
        car.work();
        car.getTransmission().getKpp().setBack();
        car.setBrakeForce(1);
        car.work();
        System.out.println(car.toString());
    }
}
