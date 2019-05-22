package View.ViewModel;
import Exceptions.InvalidNewRental;
import Model.Car;
import Utils.Point;

public class AutonomyCar {
    Point point;
    int autonomy;
    Car.CarType type;

    public AutonomyCar(Point point, int autonomy, String type) throws InvalidNewRental {
        try {
            this.type = Car.CarType.valueOf(type.toLowerCase());
        }
        catch (IllegalArgumentException e){
            throw new InvalidNewRental();
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
