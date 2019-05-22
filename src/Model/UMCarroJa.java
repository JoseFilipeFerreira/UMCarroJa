package Model;

import Exceptions.*;
import Utils.Point;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UMCarroJa implements Serializable {
    private final Cars cars;
    private final Users users;
    private final Rentals rentals;

    public UMCarroJa() {
        this.cars = new Cars();
        this.users = new Users();
        this.rentals = new Rentals();
    }

    public List<Entry<String, Double>> getBestClients() {
        return this
                .users
                .getClientIDS()
                .stream()
                .collect(Collectors
                        .toMap(Function.identity(),
                                (e) -> rentals.getRentalListClient(e)
                                        .stream()
                                        .map(Rental::getDistance)
                                        .reduce(0.0, Double::sum)))
                .entrySet()
                .stream()
                .sorted(Collections
                        .reverseOrder(Comparator
                                .comparingDouble(Entry::getValue)))
                .collect(Collectors.toList());
    }

    void rental(String username, Point dest, String preference, Car.CarType a)
            throws UnknownCompareTypeException, NoCarAvaliableException, InvalidUserException {
        Client c = (Client) users.getUser(username);
        Car car = cars.getCar(preference, dest, c.getPos(), a);
        Rental r = new Rental(car, c, dest);
        car.pendingRental(r);
        this.rent(r);
    }

    //Range a pe
    public Rental rental(Client c, Point dest, double range, Car.CarType a)
            throws NoCarAvaliableException{
        Car car = cars.getCar(dest, c.getPos(), range, a);
        Rental r = new Rental(car, c, dest);
        car.pendingRental(r);
        return r;
    }

    //MaisPerto e MaisBarato
    public Rental rental(Client c, Point dest, String preference, Car.CarType a)
            throws UnknownCompareTypeException, NoCarAvaliableException {
        Car car = cars.getCar(preference, dest, c.getPos(), a);
        Rental r = new Rental(car, c, dest);
        car.pendingRental(r);
        return r;
    }

    public void rent(Rental r) {
        rentals.addRental(r);
        r.rent();
    }

    public void addUser(User a) throws UserExistsException {
        this.users.addUser(a.clone());
    }

    void addCar(String numberPlate, String ownerID, Car.CarType type, double avgSpeed, double basePrice, double gasMileage, int range, Point pos, String brand) throws CarExistsException, InvalidUserException {
        Owner o = (Owner) this.users.getUser(ownerID);
        Car a = new Car(numberPlate, o, type, avgSpeed, basePrice, gasMileage, range, pos, brand);
        this.cars.addCar(a);
        o.addCar(a);
    }

    public void addCar(Owner os, String numberPlate, Car.CarType type, double avgSpeed, double basePrice, double gasMileage, int range, Point pos, String brand) throws CarExistsException, InvalidUserException {
        Owner o = (Owner) this.users.getUser(os.getEmail());
        Car a = new Car(numberPlate, o, type, avgSpeed, basePrice, gasMileage, range, pos, brand);
        this.cars.addCar(a);
        o.addCar(a);
    }

    void rate(String s, int rate) throws InvalidUserException, InvalidCarException {
        try {
            this.users
                    .getUser(Integer.parseInt(s) + "@gmail.com")
                    .rate(rate);
        }
        catch(NumberFormatException ignored) {
            this.cars
                    .searchCar(s)
                    .rate(rate);
        }
    }

    public User logIn(String username, String passwd) throws InvalidUserException, WrongPasswordExecption {
        User c = users.getUser(username);
        if(!c.getPasswd().equals(passwd))
            throw new WrongPasswordExecption();
        return c;
    }

    public void save(String fName) throws IOException {
        FileOutputStream a = new FileOutputStream(fName);
        ObjectOutputStream r = new ObjectOutputStream(a);
        r.writeObject(this);
        r.flush();
        r.close();
    }

    public static UMCarroJa read(String fName) throws IOException, ClassNotFoundException {
        FileInputStream r = new FileInputStream(fName);
        ObjectInputStream a = new ObjectInputStream(r);
        UMCarroJa u = (UMCarroJa) a.readObject();
        a.close();
        return u;
    }
}
