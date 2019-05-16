package Utils;

public class Point {
    private double x;
    private double y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point x) {
        this.x = x.getX();
        this.y = x.getY();
    }

    public Double getX() {
        return this.x;
    }

    public Double getY() {
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
        return "( " + this.x + ", " + this.y + " )";
    }
}