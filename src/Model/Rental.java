package Model;

import Utils.Point;

import java.time.LocalDateTime;

public class Rental {
    private String clientID;
    private String carID;
    private Point start;
    private Point end;
    private double price;
    private LocalDateTime date;

    public Rental(String client, String car, Point start, Point end, double price, LocalDateTime date) {
        this.clientID = client;
        this.carID = car;
        this.start = start;
        this.end = end;
        this.price = price;
        this.date = date;
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

    public String getClientID() {
        return this.clientID;
    }

    public Rental clone() {
        return new Rental(this);
    }
}
