package Model;

import Utils.Point;

import java.time.LocalDateTime;

public class Rental {
    private int clientID;
    private String carID;
    private Point start;
    private Point end;
    private double price;
    private LocalDateTime date;

    public Rental(Car car, Client client, Point dest) {
        this.clientID = client.getNif();
        this.carID = car.getNumberPlate();
        this.start = car.getPosition();
        this.end = dest;
        this.price = car.getBasePrice() * start.distanceBetweenPoints(dest);
        this.date = LocalDateTime.now();
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public Point getStart() {
        return this.start;
    }

    public double getDistance() {
        return this.getStart().distanceBetweenPoints(this.getEnd());
    }

    public Point getEnd() {
        return this.end;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCarID() {
        return this.carID;
    }

    public int getClientID() {
        return this.clientID;
    }

}
