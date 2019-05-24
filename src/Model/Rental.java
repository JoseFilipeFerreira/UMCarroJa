package Model;

import Utils.Point;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Rental implements Serializable {
    private final Client client;
    private final Car car;
    private final Point start;
    private final Point end;
    private final double price;
    private final LocalDateTime date;

    Rental(Car car, Client client, Point dest) {
        this.client = client;
        this.car = car;
        this.start = car.getPosition();
        this.end = dest;
        this.price = car.getBasePrice() * start.distanceBetweenPoints(dest);
        this.date = LocalDateTime.now();
    }

    LocalDateTime getDate() {
        return this.date;
    }

    private Point getStart() {
        return this.start;
    }

    double getDistance() {
        return this.getStart().distanceBetweenPoints(this.getEnd());
    }

    private Point getEnd() {
        return this.end;
    }

    double getPrice() {
        return this.price;
    }

    String getCarID() {
        return this.car.getNumberPlate();
    }

    String getClientID() {
        return this.client.getEmail();
    }

    String getOwnerID() {
        return this.car.getOwnerID();
    }

    void rent() {
        this.client.setPos(this.end);
        this.car.setPosition(this.end);
        this.car.approvePendingRental(this);
        this.client.addPendingRental(this);
    }

    void rate(int carRate, int ownerRate) {
        this.car.rate(carRate, ownerRate);
    }

    void rate(int clientRate) {
        this.client.rate(clientRate);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Cliente: ").append(this.client.getEmail()).append("\n");
        str.append("Carro:   ").append(this.car.getNumberPlate()).append("\n");
        str.append("Dono:    ").append(this.car.getOwnerID()).append("\n");
        str.append("Viagem:  ").append(this.start).append(" -> ").append(this.end).append("\n");
        str.append("Custo:   ").append(String.format("%.2f", this.price));
        return str.toString();
    }
}
