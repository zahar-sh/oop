package cars;

import cars.car.BenzineCar;
import cars.car.ElectroCar;
import cars.chassis.Chassis;
import cars.chassis.Wheel;
import cars.engine.BensineEngine;
import cars.engine.ElectoEngine;
import cars.fuel.Bensine;
import cars.fuel.Electricity;
import cars.fuelStorage.Battery;
import cars.fuelStorage.BenzineFS;
import cars.transmission.*;

public class CarFactory {
    public static Body createBody1() {
        return new Body(3000, 1900, 1800, 2100, 4);
    }
    public static Body createBody2() {
        return new Body(3000, 1900, 1800, 2900, 6);
    }
    public static Body createBody3() {
        return new Body(3000, 1900, 1800, 3400, 8);
    }

    public static BensineEngine createBensineEngine1() {
        return new BensineEngine(new Bensine(30), createBenzineFuelStorage1());
    }
    public static BensineEngine createBensineEngine2() {
        return new BensineEngine(new Bensine(30), createBenzineFuelStorage2());
    }
    public static ElectoEngine createElectroEngine1() {
        return new ElectoEngine(new Electricity(30), createBattery1());
    }
    public static ElectoEngine createElectroEngine2() {
        return new ElectoEngine(new Electricity(30), createBattery2());
    }

    public static Battery createBattery1() {
        return new Battery(new Electricity(500), new Electricity(300));
    }
    public static Battery createBattery2() {
        return new Battery(new Electricity(500), new Electricity(300));
    }

    public static BenzineFS createBenzineFuelStorage1() {
        return new BenzineFS(new Bensine(500), new Bensine(300));
    }
    public static BenzineFS createBenzineFuelStorage2() {
        return new BenzineFS(new Bensine(500), new Bensine(300));
    }

    public static KPP createKpp1() {
        return new KPP(new Gear[]{
                Gear.NEUTRAL,
                new Gear(0.1F),
                new Gear(0.2F),
                new Gear(0.3F),
                new Gear(0.5F),
                new Gear(0.7F),
                new Gear(0.9F),
                new Gear(-0.1F),
        });
    }
    public static KPP createKpp2() {
        return new KPP(new Gear[]{
                Gear.NEUTRAL,
                new Gear(0.1F),
                new Gear(0.2F),
                new Gear(0.3F),
                new Gear(0.5F),
                new Gear(0.7F),
                new Gear(0.9F),
                new Gear(-0.1F),
        });
    }

    public static Chassis createChassis1() {
        return new Chassis(30,
                new Wheel(), new Wheel(),
                new Wheel(), new Wheel());
    }

    public static FrontTr createFrontTr11() {
        return new FrontTr(createKpp1(), createChassis1());
    }
    public static FrontTr createFrontTr21() {
        return new FrontTr(createKpp2(), createChassis1());
    }

    public static BackTr createBackTr11() {
        return new BackTr(createKpp1(), createChassis1());
    }
    public static BackTr createBackTr21() {
        return new BackTr(createKpp2(), createChassis1());
    }

    public static FullTr createFullTr11() {
        return new FullTr(createKpp1(), createChassis1());
    }
    public static FullTr createFullTr21() {
        return new FullTr(createKpp2(), createChassis1());
    }

    public static ElectroCar createElectroCar1() {
        return new ElectroCar(createBody1(), createElectroEngine1(), createBackTr21());
    }
    public static ElectroCar createElectroCar2() {
        return new ElectroCar(createBody2(), createElectroEngine2(), createFullTr11());
    }
    public static ElectroCar createElectroCar3() {
        return new ElectroCar(createBody3(), createElectroEngine1(), createBackTr11());
    }

    public static BenzineCar createBenzineCar1() {
        return new BenzineCar(createBody3(), createBensineEngine1(), createFrontTr21());
    }
    public static BenzineCar createBenzineCar2() {
        return new BenzineCar(createBody1(), createBensineEngine2(), createFullTr21());
    }
    public static BenzineCar createBenzineCar3() {
        return new BenzineCar(createBody2(), createBensineEngine2(), createBackTr11());
    }
}