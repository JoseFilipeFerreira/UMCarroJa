package Model;

import Exceptions.UnknownCarTypeException;
import Utils.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Car implements Serializable {
    private final String numberPlate;
    private final Owner owner;

    private final String brand;
    private final CarType type;
    private final double avgSpeed;
    private final double basePrice;
    private final double gasMileage;
    private Point position;
    private final int fullTankRange;

    private boolean isAvailable;

    private int range;
    private final int rating;
    private final int nRatings;

    private final List<Rental> historic;

    private Car(Car car) {
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
        this.historic = new ArrayList<>(car.historic);
    }

    public enum CarType {
        electric,
        gas,
        hybrid,
        any;

        public boolean equals(CarType a) {
            return a == this || a == any;
        }

        public static CarType fromString(String s) throws UnknownCarTypeException {
            switch (s) {
                case "Electrico":
                    return CarType.electric;
                case "Gasolina":
                    return CarType.gas;
                case "Hibrido":
                    return CarType.hybrid;
                case "Todos":
                    return CarType.any;
            }
            throw new UnknownCarTypeException();
        }
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

    private double getAvgSpeed() {
        return this.avgSpeed;
    }

    private int getFullTankRange() {
        return this.fullTankRange;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    private double getGasMileage() {
        return this.gasMileage;
    }

    private int getRating() {
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

    private int getNRatings() {
        return this.nRatings;
    }

    private String getBrand() {
        return this.brand;
    }

    private boolean isAvailable() {
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
        this.historic = new ArrayList<>();
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
