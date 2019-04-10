import java.util.ArrayList;

public class Owner extends Users{
    private int rating;
    private ArrayList<String> rentalHistoric;

    public Owner() {
        super(email, name, address, dateOfBirth);
    }
}
