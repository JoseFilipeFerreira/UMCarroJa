package Model;

import java.util.ArrayList;
import java.util.List;

public class Owner extends User {
    private List<Car> cars;
    private List<Rental> pending;
    private List<Rental> historic;

    public Owner(String email, String name, String address, int nif, String passwd) {
        super(email, name, address, nif, passwd);
        this.cars = new ArrayList<>();
        this.pending = new ArrayList<>();
        this.historic = new ArrayList<>();
    }

    private Owner(User u) {
        super(u);
        if (u instanceof Owner) {
            Owner o = (Owner) u;
            this.cars = o.getCars();
            this.pending = new ArrayList<>(o.pending);
            this.historic = new ArrayList<>(o.historic);
        }
    }

    public ArrayList<Rental> getPending() {
        return new ArrayList<>(this.pending);
    }

    void addPendingRental(Rental r) {
        this.pending.add(r);
    }

    public void refuse(Rental r){
        this.pending.remove(r);
    }

    void accept(Rental r) {
        this.refuse(r);
        this.historic.add(r);
    }

    public void rate(Rental r, int clientRate) {
        r.rate(clientRate);
        this.historic.remove(r);
    }

    void addCar(Car a) {
        this.cars.add(a);
    }

    public List<Car> getCars() {
        return new ArrayList<>(this.cars);
    }

    public Owner clone() {
        return new Owner(this);
    }
}

