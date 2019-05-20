package Utils;

import java.io.Serializable;

public class Point implements Serializable {
    private final double x;
    private final double y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    private Point(Point x) {
        this.x = x.getX();
        this.y = x.getY();
    }

    private Double getX() {
        return this.x;
    }

    private Double getY() {
        return y;
    }

    public Point clone() {
        return new Point(this);
    }

    public double distanceBetweenPoints(Point a) {
         return Math.sqrt(Math.pow(a.x - this.x, 2) + Math.pow(a.y - this.y, 2));
    }

    @Override
    public String toString() {
        return "( " + String.format("%.2f", this.x) + ", " + String.format("%.2f", this.y) + " )";
    }
}