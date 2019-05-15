package Model;

import Utils.Point;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;

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
        this.start = client.getPos();
        this.end = dest;
        this.price = car.getBasePrice() * start.distanceBetweenPoints(dest);
        this.date = LocalDateTime.now();
    }

    public Rental(Rental rental) {
        this.clientID = rental.getClientID();
        this.carID = rental.getCarID();
        this.start = rental.getStart();
        this.end = rental.getEnd();
        this.price = rental.getPrice();
        this.date = rental.getDate();
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

    public Rental clone() {
        return new Rental(this);
    }
}
