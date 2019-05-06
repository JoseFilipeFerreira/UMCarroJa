package Utils;

public class Point {
    private int x;
    private int y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point x) {
        this.x = x.getX();
        this.y = x.getY();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    public Point clone() {
        return new Point(this);
    }

    public double distanceBetweenPoints(Point a) {
         return Math.sqrt((a.x - this.x)^2 + (a.y - this.y)^2);
    }
}