package View.ViewModel;

import Utils.Point;

public class SpecificCar {
    Point point;
    String numberPlate;

    public SpecificCar(Point point, String numberPlate) {
        this.point = point;
        this.numberPlate = numberPlate;
    }

    public Point getPoint() {
        return point;
    }

    public String getNumberPlate() {
        return numberPlate;
    }
}
