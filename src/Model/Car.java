package Model;

import Utils.Point;

import java.util.ArrayList;

public class Car {
    private String numberPlate;
    private String ownerID;

    private CarType type;
    private double avgSpeed;
    private double basePrice;
    private double gasMileage;

    private int rating;
    private ArrayList<Integer> rentalHistoric;
    private Point position;
    private double range;

    public enum CarType {
        Electric,
        Gas,
        Hybrid

    }

    public String getOwnerID() {
        return this.ownerID;
    }

    public double getAvgSpeed() {
        return this.avgSpeed;
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

    public ArrayList<Integer> getRentalHistoric() {
        return new ArrayList<>(this.rentalHistoric);
    }

   public Point getPosition() {
        return this.position;
    }

   public double getRange() {
        return this.range;
    }

    public CarType getType() {
        return this.type;
    }

    public String getNumberPlate() {
        return this.numberPlate;
    }

    public Car(String numberPlate, String ownerID, CarType type, double avgSpeed, double basePrice, double gasMileage, int rating, Point position, double range) {
        this.numberPlate = numberPlate;
        this.ownerID = ownerID;
        this.type = type;
        this.avgSpeed = avgSpeed;
        this.basePrice = basePrice;
        this.gasMileage = gasMileage;
        this.rating = rating;
        this.position = position;
        this.range = range;
    }

    public Car(Car a) {
        this.numberPlate = a.getNumberPlate();
        this.ownerID = a.getOwnerID();
        this.type = a.getType();
        this.avgSpeed = a.getAvgSpeed();
        this.basePrice = a.getBasePrice();
        this.gasMileage = a.getGasMileage();
        this.rating = a.getRating();
        this.position = a.getPosition();
        this.range = a.getRange();
        this.rentalHistoric = a.getRentalHistoric();
    }

    public Car clone() {
        return new Car(this);
    }
}
