package Model;

import Exceptions.UnknownCarTypeException;
import Utils.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Car implements Serializable {
    private static final long serialVersionUID = -1292370800088543472L;
    private final String numberPlate;
    private final Owner owner;

    private final String brand;
    private final CarType type;
    private final double avgSpeed;
    private double basePrice;
    private final double gasMileage;
    private final Point position;
    private final int fullTankRange;

    private boolean isAvailable;

    private double range;
    private int rating;
    private int nRatings;

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

    void setPosition(Point position, double delay) {
        this.range -= this
                .position
                .distanceBetweenPoints(position) * (1 + (delay % 0.2));
    }

    void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    private Owner getOwner() {
        return this.owner;
    }

    String getOwnerID() {
        return this.owner.getEmail();
    }

    double getAvgSpeed() {
        return this.avgSpeed;
    }

    private int getFullTankRange() {
        return this.fullTankRange;
    }

    double getBasePrice() {
        return this.basePrice;
    }

    private double getGasMileage() {
        return this.gasMileage;
    }

    private int getRating() {
        return (this.nRatings == 0)? 100 : (this.rating / this.nRatings);
    }

    Point getPosition() {
        return this.position;
    }

    double getRange() {
        return this.range;
    }

    CarType getType() {
        return this.type;
    }

    String getNumberPlate() {
        return this.numberPlate;
    }

    private int getNRatings() {
        return this.nRatings;
    }

    private String getBrand() {
        return this.brand;
    }

    boolean isAvailable() {
        return this.isAvailable;
    }

    Car(String numberPlate, Owner owner, CarType type,
            double avgSpeed, double basePrice, double gasMileage, int range, Point pos, String brand) {
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

    void swapState() {
        this.isAvailable = !this.isAvailable;
    }

    void refil() {
        this.range = this.fullTankRange;
    }

    boolean hasRange(Point dest) {
        if(this.range / this.getFullTankRange() < 0.1) return false;
        return !(this.position.distanceBetweenPoints(dest) * 1.2 > this.range);
    }

    void rate(int rating) {
        this.nRatings++;
        this.rating += rating;
    }

    void rate(int carRating, int ownerRate) {
        this.rate(carRating);
        this.owner.rate(ownerRate);
    }

    void pendingRental(Rental r) {
        this.owner.addPendingRental(r);
    }

    void approvePendingRental(Rental r) {
        this.owner.accept(r);
        this.historic.add(r);
    }

    public Car clone() {
        return new Car(this);
    }

    public String warnings() {
        StringBuilder a = new StringBuilder();
        if(this.range / this.getFullTankRange() < 0.15)
            a.append("O carro necessita de ser abastecido\n");
        return a.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        Car car = (Car) o;
        return this.avgSpeed == car.avgSpeed
                && this.basePrice == car.basePrice
                && this.gasMileage == car.gasMileage
                && this.fullTankRange == car.fullTankRange
                && this.isAvailable == car.isAvailable
                && this.range == car.range
                && this.rating == car.rating
                && this.nRatings == car.nRatings
                && this.numberPlate.equals(car.numberPlate)
                && this.owner.equals(car.owner)
                && this.brand.equals(car.brand)
                && this.type == car.type
                && this.position.equals(car.position)
                && this.historic.equals(car.historic);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getNumberPlate()).append("\n")
                .append(String.format("%.2f", this.getRange())).append("\n")
                .append(String.format("%.2f", this.getBasePrice())).append("\n")
                .append(this.isAvailable).append("\n")
                .append(this.getRating()).toString();
    }
}
