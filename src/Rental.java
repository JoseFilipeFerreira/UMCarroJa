public class Rental {
    //ids or objects ?
    private Client client;
    private Car car;
    private Point start;
    private Point end;
    private double distance;
    private double price;

    public Rental(Client client, Car car, Point start, Point end, double distance, double price) {
        this.client = client;
        this.car = car;
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.price = price;
    }

    public Car getCar() {
        return this.car.clone();
    }

    public void setCar(Car car) {
        this.car = car.clone();
    }

    public Client getClient() {
        return this.client; //needs fixes
    }
}
