import java.util.ArrayList;

public class Car {
    private int type;
    private double avgSpeed;
    private double basePrice;
    private double gasMilage;
    private ArrayList<Rental> rentalHistoric;
    private int rating;

    private Point position;
    private double range;

    public Car(int type, double avgSpeed, double basePrice, double gasMilage, int rating, Point position, double range) {
        this.type = type;
        this.avgSpeed = avgSpeed;
        this.basePrice = basePrice;
        this.gasMilage = gasMilage;
        this.rentalHistoric = new ArrayList<>();
        this.rating = rating;
        this.position = position;
        this.range = range;
    }
}
