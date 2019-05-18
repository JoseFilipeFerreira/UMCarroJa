package Model;

import Utils.Point;

import java.time.LocalDateTime;

public class Rental {
    private Client client;
    private Car car;
    private Point start;
    private Point end;
    private double price;
    private LocalDateTime date;

    public Rental(Car car, Client client, Point dest) {
        this.client = client;
        this.car = car;
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
        return this.car.getNumberPlate();
    }

    public String getClientID() {
        return this.client.getEmail();
    }

    public void rent() {
        this.client.setPos(this.end);
        this.car.setPosition(this.end);
        this.car.removePendingRental(this);
        this.client.addPendingRental(this);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Cliente: ").append(this.client.getEmail()).append("\n");
        str.append("Carro: ").append(this.car.getNumberPlate()).append("\n");
        str.append("Dono: ").append(this.car.getOwnerID()).append("\n");
        str.append("Viagem: ").append(this.start).append(" -> ").append(this.end).append("\n");
        str.append("Custo: ").append(String.format("%.2f", this.price));
        return str.toString();
    }
}
