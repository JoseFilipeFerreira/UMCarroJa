package View.ViewModel;
import Exceptions.InvalidNewRentalException;
import Model.Car;
import Utils.Point;

public class RentCarSimple {
    private final Point point;
    private final Car.CarType type;

    public RentCarSimple(Point point, String type) throws InvalidNewRentalException {

        this.point = point;
        try {
            this.type = Car.CarType.valueOf(type.toLowerCase());
        }
        catch (IllegalArgumentException e){
            throw new InvalidNewRentalException();
        }
    }

    public Point getPoint() {
        return this.point;
    }

    public Car.CarType getCarType() {
        return this.type;
    }
}
