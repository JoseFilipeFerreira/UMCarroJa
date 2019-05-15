package Model;

import Utils.Point;

import java.time.LocalDateTime;

public class Client extends User {
    private Point pos;

    public Client(Point pos, String email, String passwd, String name, String address, int nif) {
        super(email, name, address, nif, passwd);
        this.pos = pos;
    }

    public Point getPos() {
        return this.pos.clone();
    }

    public Client(Client u) {
        super(u);
        this.pos = u.getPos().clone();
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public Client clone() {
        return new Client(this);
    }

}
