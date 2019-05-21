package View.ViewModel;

import Exceptions.InvalidNewRegister;
import Exceptions.UnknownCarTypeException;
import Model.Car;
import Model.Owner;
import Utils.Point;

public class RegisterCar {
    private String numberPlate;
    private Car.CarType type;
    private double avgSpeed;
    private double basePrice;
    private double gasMileage;
    private int range;
    private Point pos;
    private String brand;

    public RegisterCar(String numberPlate, String type, double avgSpeed, double basePrice, double gasMileage, int range, Point pos, String brand) throws InvalidNewRegister {
        this.numberPlate = numberPlate;
        try {
            this.type = Car.CarType.valueOf(type.toLowerCase());
        }
        catch (IllegalArgumentException e){
            throw new InvalidNewRegister();
        }
        this.avgSpeed = avgSpeed;
        this.basePrice = basePrice;
        this.gasMileage = gasMileage;
        this.range = range;
        this.pos = pos;
        this.brand = brand;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public Car.CarType getType() {
        return type;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getGasMileage() {
        return gasMileage;
    }

    public int getRange() {
        return range;
    }

    public Point getPos() {
        return pos;
    }

    public String getBrand() {
        return brand;
    }
}