package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Owner extends User {
    private int rating;
    private int nRentals;
    private ArrayList<String> carIDs;

    public Owner(String email, String name, String address, int nif, String passwd) {
        super(email, name, address, nif, passwd);
        this.rating = 0;
        this.nRentals = 0;
        this.carIDs = new ArrayList<>();
    }

    public Owner(User u) {
        super(u);
        if (u instanceof Owner) {
            Owner o = (Owner) u;
            this.rating = o.getRating();
            this.nRentals = o.getnRentals();
            this.carIDs = o.getCarIDs();
        }
    }

    public void rate(int rating) {
        this.rating += rating;
        this.nRentals++;
    }

    public void addCar(Car a) {
        this.carIDs.add(a.getNumberPlate());
    }

    public int getRating() {
        return this.rating;
    }

    public int getnRentals() {
        return this.nRentals;
    }

    public ArrayList<String> getCarIDs() {
        return new ArrayList<>(this.carIDs);
    }

    public Owner clone() {
        return new Owner(this);
    }
}

