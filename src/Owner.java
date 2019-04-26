import java.time.LocalDateTime;
import java.util.ArrayList;

public class Owner extends Users {
    private int rating;
    private int nRentals;
    private ArrayList<String> carIDs;

    public Owner(String email, String name, String address, LocalDateTime dateOfBirth) {
        super(email, name, address, dateOfBirth);
        this.rating = 0;
        this.nRentals = 0;
        this.carIDs = new ArrayList<>();
    }

    public Owner(Users u) {
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

