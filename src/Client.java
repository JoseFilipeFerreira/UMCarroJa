import java.util.ArrayList;
import java.time.LocalDateTime;

public class Client extends Users{
    private Point pos;

    public Client(Point pos, String email, String name, String address, LocalDateTime dateOfBirth) {
        super(email, name, address, dateOfBirth);
        this.pos = pos;
    }

    public Point getPos() {
        return this.pos.clone();
    }

    public Client(Users u) {
        super(u);
        if(u instanceof Client)
            this.pos = ((Client) u).getPos().clone();
    }

    public Client clone() {
        return new Client(this);
    }
}
