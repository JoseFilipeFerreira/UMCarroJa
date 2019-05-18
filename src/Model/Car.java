package Model;

import Exceptions.UnknownCarTypeException;
import Utils.Point;

public class Car {
    private String numberPlate;
    private Owner owner;

    private String brand;
    private CarType type;
    private double avgSpeed;
    private double basePrice;
    private double gasMileage;
    private Point position;
    private int fullTankRange;

    private boolean isAvailable;

    private int range;
    private int rating;
    private int nRatings;

    public Car(Car car) {
        this.numberPlate = car.getNumberPlate();
        this.owner = car.getOwner();
        this.brand = car.getBrand();
        this.type = car.getType();
        this.avgSpeed = car.getAvgSpeed();
        this.basePrice = car.getBasePrice();
        this.gasMileage = car.getGasMileage();
        this.position = car.getPosition();
        this.fullTankRange = car.getFullTankRange();
        this.range = car.getRange();
        this.rating = car.getRating();
        this.nRatings = car.getNRatings();
        this.isAvailable = car.isAvailable();
    }

    public enum CarType {
        Electric,
        Gas,
        Hybrid,
        Any
    }

    public static CarType fromString(String s) throws UnknownCarTypeException {
        switch (s) {
            case "Electrico":
                return CarType.Electric;
            case "Gasolina":
                return CarType.Gas;
            case "Hibrido":
                return CarType.Hybrid;
            case "Todos":
                return CarType.Any;
        }
        throw new UnknownCarTypeException();
    }

    public void setPosition(Point position) {
        this.position = position;
        this.range -= this
                .position
                .distanceBetweenPoints(position);
    }

    private Owner getOwner() {
        return this.owner;
    }

    public String getOwnerID() {
        return this.owner.getEmail();
    }

    public double getAvgSpeed() {
        return this.avgSpeed;
    }

    public int getFullTankRange() {
        return this.fullTankRange;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public double getGasMileage() {
        return this.gasMileage;
    }

    public int getRating() {
        return this.rating;
    }

    public Point getPosition() {
        return this.position;
    }

    public int getRange() {
        return this.range;
    }

    public CarType getType() {
        return this.type;
    }

    public String getNumberPlate() {
        return this.numberPlate;
    }

    public int getNRatings() {
        return this.nRatings;
    }

    public String getBrand() {
        return this.brand;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public Car(String numberPlate, Owner owner, CarType type, double avgSpeed, double basePrice, double gasMileage, int range, Point pos, String brand) {
        this.numberPlate = numberPlate;
        this.owner = owner;
        this.type = type;
        this.avgSpeed = avgSpeed;
        this.basePrice = basePrice;
        this.gasMileage = gasMileage;
        this.fullTankRange = range;
        this.range = this.fullTankRange;
        this.brand = brand;
        this.position = pos;
        this.rating = 0;
        this.nRatings = 0;
        this.isAvailable = true;
    }

    public void swapState() {
        this.isAvailable = !this.isAvailable;
    }

    public boolean hasRange(Point dest) {
        if((double)this.range / this.getFullTankRange() < 0.1) return false;
        return !(this.position.distanceBetweenPoints(dest) > this.range);
    }

    public void addCarUser() {
        this.owner.addCar(this);
    }

    public void pendingRental(Rental r) {
        this.owner.addPendingRental(r);
    }

    public void removePendingRental(Rental r) {
        this.owner.refuse(r);
    }

    public Car clone() {
        return new Car(this);
    }
}
