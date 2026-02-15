import java.util.*;

// --- ENUMS ---
enum VehicleStatus { AVAILABLE, RESERVED, RENTED }
enum ReservationStatus { PENDING, CONFIRMED, CANCELED }

// --- STRATEGY PATTERN: PAYMENT ---
interface PaymentStrategy {
    void processPayment(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card payment: $" + amount);
    }
}

// --- FACTORY PATTERN: VEHICLES ---
abstract class Vehicle {
    protected String id;
    protected double basePrice;
    protected VehicleStatus status = VehicleStatus.AVAILABLE;

    public abstract double calculateFee(int days);
    public void setStatus(VehicleStatus status) { this.status = status; }
    public VehicleStatus getStatus() { return status; }
}

class Car extends Vehicle {
    public Car(String id, double basePrice) { this.id = id; this.basePrice = basePrice; }
    @Override
    public double calculateFee(int days) { return basePrice * days; }
}

class VehicleFactory {
    public static Vehicle createVehicle(String type, String id, double price) {
        if (type.equalsIgnoreCase("CAR")) return new Car(id, price);
        return null; // Easily extendable to BIKE or SUV
    }
}

// --- CORE ENTITIES ---
class User {
    int id;
    String name;
    public User(int id, String name) { this.id = id; this.name = name; }
}

class Reservation {
    Vehicle vehicle;
    User user;
    int days;
    double totalAmount;
    ReservationStatus status;

    public Reservation(User user, Vehicle vehicle, int days) {
        this.user = user;
        this.vehicle = vehicle;
        this.days = days;
        this.totalAmount = vehicle.calculateFee(days);
        this.status = ReservationStatus.PENDING;
    }

    public void confirm() {
        this.status = ReservationStatus.CONFIRMED;
        vehicle.setStatus(VehicleStatus.RESERVED);
        System.out.println("Reservation confirmed for " + user.name + ". Total: $" + totalAmount);
    }
}

// --- SINGLETON SYSTEM ---
class RentalSystem {
    private static RentalSystem instance;
    private Map<String, Vehicle> inventory = new HashMap<>();

    private RentalSystem() {}

    public static synchronized RentalSystem getInstance() {
        if (instance == null) instance = new RentalSystem();
        return instance;
    }

    public void addVehicle(Vehicle v) { inventory.put(v.id, v); }

    public Reservation makeReservation(User user, String vehicleId, int days) {
        Vehicle vehicle = inventory.get(vehicleId);
        if (vehicle != null && vehicle.getStatus() == VehicleStatus.AVAILABLE) {
            return new Reservation(user, vehicle, days);
        }
        return null;
    }

    public void processPayment(Reservation res, PaymentStrategy strategy) {
        strategy.processPayment(res.totalAmount);
        res.confirm();
    }
}

// --- RUNNABLE MAIN ---
public class CarRentalApp {
    public static void main(String[] args) {
        RentalSystem system = RentalSystem.getInstance();

        // 1. Setup Data
        Vehicle myCar = VehicleFactory.createVehicle("CAR", "V123", 50.0);
        system.addVehicle(myCar);
        User user = new User(1, "John Doe");

        // 2. Client Flow
        System.out.println("--- Starting Reservation ---");
        Reservation res = system.makeReservation(user, "V123", 3);

        if (res != null) {
            system.processPayment(res, new CreditCardPayment());
        } else {
            System.out.println("Vehicle unavailable.");
        }
    }
}