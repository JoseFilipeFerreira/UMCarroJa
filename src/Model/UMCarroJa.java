package Model;

import Exceptions.*;
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

    public List<String> getBestClients() {
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

    public Rental rental(String username, Point dest, String preference) throws UnknownCompareTypeException {
        Client c = (Client) users.getUser(username);
        Car car = cars.getCar(preference, dest, c.getPos());
        Rental r = new Rental(car, c, dest);
        car.pendingRental(r);
        this.rent(r);
        return r;
    }

    public Rental rental(Client c, Point dest, double range) throws NoCarAvaliableException{
        Car car = cars.getCar(dest, c.getPos(), range);
        Rental r = new Rental(car, c, dest);
        rentals.addRental(r);
        c.setPos(dest);
        car.setPosition(dest);
        return r;
    }

    public Rental rental(Client c, Point dest, String preference) throws UnknownCompareTypeException {
        Car car = cars.getCar(preference, dest, c.getPos());
        Rental r = new Rental(car, c, dest);
        car.pendingRental(r);
        return r;
    }

    public void rent(Rental r) {
        rentals.addRental(r);
        r.rent();
    }

    public void addUser(User a) throws UserExistsException {
        this.users.addUser(a);
    }

    public void addCar(String numberPlate, String ownerID, Car.CarType type, double avgSpeed, double basePrice, double gasMileage, int range, Point pos, String brand) throws CarExistsException {
        Owner o = (Owner) this.users.getUser(ownerID);
        Car a = new Car(numberPlate, o, type, avgSpeed, basePrice, gasMileage, range, pos, brand);
        this.cars.addCar(a);
        a.addCarUser();
    }

    public void addCar(Car a) throws CarExistsException {
        this.cars.addCar(a);
        a.addCarUser();
    }

    public User logIn(String username, String passwd) throws InvalidUserException, WrongPasswordExecption {
        User c = users.getUser(username);
        if(c == null)
            throw new InvalidUserException();
        if(!c.getPasswd().equals(passwd))
            throw new WrongPasswordExecption();
        return c;
    }
}
