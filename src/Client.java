import java.util.ArrayList;
import java.time.LocalDateTime;

public class Client extends Users{
    private Point pos;
    private ArrayList<Rental> rentalHistoric;

    public Client(Point pos, String email, String name, String address, LocalDateTime dateOfBirth) {
        super(email, name, address, dateOfBirth);
        this.pos = pos;
        this.rentalHistoric = new ArrayList<>();
    }
}
