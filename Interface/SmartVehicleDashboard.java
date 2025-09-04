interface VehicleDashboard {
    void displaySpeed();
    default void displayBattery() {
        System.out.println("Battery info not available");
    }
}

class PetrolCar implements VehicleDashboard {
    public void displaySpeed() { System.out.println("Petrol car speed 80 km/h"); }
}

class ElectricCar implements VehicleDashboard {
    public void displaySpeed() { System.out.println("EV speed 100 km/h"); }
    public void displayBattery() { System.out.println("Battery 80%"); }
}

public class SmartVehicleDashboard {
    public static void main(String[] args) {
        VehicleDashboard v1 = new PetrolCar();
        VehicleDashboard v2 = new ElectricCar();
        v1.displaySpeed(); v1.displayBattery();
        v2.displaySpeed(); v2.displayBattery();
    }
}
