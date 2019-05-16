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

    public Rental rental(Client c, Point dest, Car.CarType cartype, String preference) throws UnknownCompareTypeException {
        return subRental(c, dest, cartype, preference);
    }

    private Rental subRental(Client c, Point dest, Car.CarType cartype, String preference) throws UnknownCompareTypeException {
        Car car = cars.getCar(preference, cartype, dest, c.getPos());
        Rental r = new Rental(car, c, dest);
        rentals.addRental(r);
        c.setPos(dest);
        car.setPosition(dest);
        return r;
    }

    public Rental rental(Client c, Point dest, Car.CarType carType, double range) throws NoCarAvaliableException{
        Car car = cars.getCar(carType, dest, c.getPos(), range);
        Rental r = new Rental(car, c, dest);
        rentals.addRental(r);
        c.setPos(dest);
        car.setPosition(dest);
        return r;
    }

    public Rental rental(int username, Point dest, Car.CarType cartype, String preference) throws UnknownCompareTypeException {
        Client c = (Client) users.getUser(username);
        return subRental(c, dest, cartype, preference);
    }

    public void addUser(User a) throws UserExistsException {
        this.users.addUser(a);
    }

    public void addCar(Car a) throws CarExistsException {
        this.cars.addCar(a);
        ((Owner) users.getUser(a.getOwnerID())).addCar(a);
    }

    public void addCar(Owner u, Car a) throws CarExistsException {
        this.cars.addCar(a);
        u.addCar(a);
    }

    public User logIn(int username, String passwd) throws InvalidUserException, WrongPasswordExecption {
        User c = users.getUser(username);
        if(c == null)
            throw new InvalidUserException();
        if(!c.getPasswd().equals(passwd))
            throw new WrongPasswordExecption();
        return c;
    }
}
