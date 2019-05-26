package Model;

import Utils.Point;
import Utils.StringBetter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Rental implements Serializable {
    private static final long serialVersionUID = 7119901023330524504L;
    private final Client client;
    private final Car car;
    private final Point start;
    private final Point end;
    private final double expectedPrice;
    private double realPrice;
    private final LocalDateTime date;
    private final double expectedTime;
    private double realTime;

    Rental(Car car, Client client, Point dest) {
        this.client = client;
        this.car = car;
        this.start = car.getPosition();
        this.end = dest;
        this.expectedPrice = car.getBasePrice() * start.distanceBetweenPoints(dest);
        this.date = LocalDateTime.now();
        this.expectedTime = this.getStart().distanceBetweenPoints(this.end) / this.car.getAvgSpeed();
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
        return this.realPrice;
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
        double weather = new Weather().getSeasonDelay();
        double traffic = new Traffic().getTraficDelay(weather);
        double delay = (weather % 0.5) + (traffic % 0.5);
        this.realTime = this.expectedTime * (1 + delay);
        this.realPrice = this.expectedPrice * (1 + (delay % 0.4));
        this.client.setPos(this.end);
        this.car.setPosition(this.end, delay);
        this.car.approvePendingRental(this);
        this.client.addPendingRental(this);
    }

    void rate(int carRate, int ownerRate) {
        this.car.rate(carRate, ownerRate);
    }

    void rate(int clientRate) {
        this.client.rate(clientRate);
    }

    public String toParsableOwnerRentalString() {
        StringBuilder str = new StringBuilder();
        str.append(this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("\n");
        str.append(this.car.getNumberPlate()).append("\n");
        str.append(this.client.getEmail()).append("\n");
        str.append(this.start).append("\n").append(this.end).append("\n");
        str.append(String.format("%.2f", this.realPrice));
        return str.toString();
    }

    public String toParsableUserRentalString() {
        StringBuilder str = new StringBuilder();
        str.append(this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("\n");
        str.append(this.car.getNumberPlate()).append("\n");
        str.append(this.car.getOwnerID()).append("\n");
        str.append(this.start).append("\n").append(this.end).append("\n");
        str.append(String.format("%.2f", this.realPrice));
        return str.toString();
    }

    public String toParsableUserString() {
        StringBuilder str = new StringBuilder();
        str.append(this.client.getEmail()).append("\n");
        str.append(this.car.getNumberPlate()).append("\n");
        str.append(this.start).append("\n");
        str.append(this.end).append("\n");
        str.append(String.format("%.2f", this.client.getPos().distanceBetweenPoints(this.start)/4)).append("\n");
        str.append(String.format("%.2f", this.expectedTime)).append("\n");
        str.append(String.format("%.2f", this.expectedPrice)).append("\n");
        str.append(this.client.getRates());
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Client:        ").append(this.client.getEmail()).append("\n");
        str.append("Carro:          ").append(this.car.getNumberPlate()).append("\n");
        str.append("Dono:           ").append(this.car.getOwnerID()).append("\n");
        str.append("Viagem:         ").append(this.start).append(" -> ").append(this.end).append("\n");
        str.append("Tempo a pé      ").append(
                String.format(
                        "%.2f Horas",
                        this.client.getPos().distanceBetweenPoints(this.start)/4)).append("\n");
        str.append("Tempo Estimado: ").append(String.format("%.2f Horas", this.expectedTime)).append("\n");
        str.append("Custo Estimado: ").append(String.format("%.2f", this.expectedPrice));
        return str.toString();
    }

    public String toFinalString() {
        StringBuilder str = new StringBuilder();
        str.append("Tempo Total: ").append(String.format("%.2f Horas", this.realTime)).append("\n");
        str.append("Custo Total: ").append(String.format("%.2f", this.realPrice)).append("\n\n");
        str.append(new StringBetter(this.car.warnings()).under());
        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        Rental rental = (Rental) o;
        return this.expectedPrice == rental.expectedPrice
                && this.realPrice == rental.realPrice
                && this.expectedTime == rental.expectedTime
                && this.realTime == rental.realTime
                && this.client.equals(rental.client)
                && this.car.equals(rental.car)
                && this.start.equals(rental.start)
                && this.end.equals(rental.end)
                && this.date.equals(rental.date);
    }
}
