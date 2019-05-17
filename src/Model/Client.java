package Model;

import Utils.Point;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private Point pos;
    private List<Rental> pendingRates;

    public Client(Point pos, String email, String passwd, String name, String address, int nif) {
        super(email, name, address, nif, passwd);
        this.pos = pos;
        this.pendingRates = new ArrayList<>();
    }

    public Point getPos() {
        return this.pos.clone();
    }

    public Client(Client u) {
        super(u);
        this.pos = u.getPos().clone();
        this.pendingRates = new ArrayList<>(u.pendingRates);
    }

    public void addPendingRental(Rental r) {
        this.pendingRates.add(r);
    }

    public void removePendingRental(Rental r) {
        this.pendingRates.remove(r);
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public Client clone() {
        return new Client(this);
    }

}
