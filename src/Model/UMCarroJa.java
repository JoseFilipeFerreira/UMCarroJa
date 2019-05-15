package Model;

import Utils.Point;

import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class UMCarroJa {
    private CarBase cars;
    private UserBase users;
    private RentalBase rentals;

    public UMCarroJa() {
        this.cars = new CarBase();
        this.users = new UserBase();
        this.rentals = new RentalBase();
    }

    public List<Integer> getBestClients() {
        return this
                .users
                .getClientIDS()
                .stream()
                .collect(Collectors
                        .toMap((e)-> e,
                                (e) -> rentals.getRentalListClient(e)
                                        .stream()
                                        .map(Rental::getDistance)
                                        .reduce(0.0, Double::sum)))
                .entrySet()
                .stream()
                .sorted(Comparator
                        .comparing(Entry::getValue))
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }

    public void rental(int clientID, Point dest, Car.CarType cartype, String preference) throws UnknownCompareType{
        Client c = (Client) users.getUser(clientID);
        Car car = cars.getCar(preference, cartype, dest, c.getPos());
        Rental r = new Rental(car, c, dest);
        rentals.addRental(r);
        c.setPos(dest);
        car.setPosition(dest);
    }

    public void addUser(User a) throws UserExistsException {
        this.users.addUser(a);
    }

    public void addCar(Car a) throws CarExistsException {
        this.cars.addCar(a);
    }
}
