import org.jetbrains.annotations.NotNull;

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

    public Point(@NotNull Point x) {
        this.x = x.x;
        this.y = x.y;
    }

    public double distanceBetweenPoints(Point a, Point b) {
         return Math.sqrt((a.x - b.x)^2 + (a.y - b.y)^2);
    }
}