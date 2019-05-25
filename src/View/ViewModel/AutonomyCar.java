package View.ViewModel;
import Exceptions.InvalidNewRentalException;
import Model.Car;
import Utils.Point;

public class AutonomyCar {
    private final Point point;
    private final int autonomy;
    private final Car.CarType type;

    public AutonomyCar(Point point, int autonomy, String type) throws InvalidNewRentalException {
        try {
            this.type = Car.CarType.valueOf(type.toLowerCase());
        }
        catch (IllegalArgumentException e){
            throw new InvalidNewRentalException();
        }
        this.point = point;
        this.autonomy = autonomy;
    }

    public Point getPoint() {
        return point;
    }

    public int getAutonomy() {
        return autonomy;
    }

    public Car.CarType getType() { return type; }
}
