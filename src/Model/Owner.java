package Model;

import java.util.ArrayList;
import java.util.List;

public class Owner extends User {
    private int rating;
    private int nRentals;
    private List<Car> cars;
    private List<Rental> pending;

    public Owner(String email, String name, String address, int nif, String passwd) {
        super(email, name, address, nif, passwd);
        this.rating = 0;
        this.nRentals = 0;
        this.cars = new ArrayList<>();
        this.pending = new ArrayList<>();
    }

    private Owner(User u) {
        super(u);
        if (u instanceof Owner) {
            Owner o = (Owner) u;
            this.rating = o.getRating();
            this.nRentals = o.getnRentals();
            this.cars = o.getCars();
            this.pending = new ArrayList<>(o.pending);
        }
    }

    public void rate(int rating) {
        this.rating += rating;
        this.nRentals++;
    }

    public ArrayList<Rental> getPending() {
        return new ArrayList<>(this.pending);
    }

    public void addPendingRental(Rental r) {
        this.pending.add(r);
    }

    public void refuse(Rental r){
        this.pending.remove(r);
    }

    public void addCar(Car a) {
        this.cars.add(a);
    }

    private int getRating() {
        return this.rating;
    }

    private int getnRentals() {
        return this.nRentals;
    }

    private ArrayList<Car> getCars() {
        return new ArrayList<>(this.cars);
    }

    public Owner clone() {
        return new Owner(this);
    }
}

