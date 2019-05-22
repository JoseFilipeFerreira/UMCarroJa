package View.ViewModel;
import Exceptions.InvalidNewRental;
import Model.Car;
import Utils.Point;

public class RentCarSimple {
    Point point;
    Car.CarType type;

    public RentCarSimple(Point point, String type) throws InvalidNewRental{

        this.point = point;
        try {
            this.type = Car.CarType.valueOf(type.toLowerCase());
        }
        catch (IllegalArgumentException e){
            throw new InvalidNewRental();
        }
    }

    public Point getPoint() {
        return this.point;
    }

    public Car.CarType getCarType() {
        return this.type;
    }
}
