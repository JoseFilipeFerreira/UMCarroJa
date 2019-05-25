package View.ViewModel;

import Utils.Point;

public class RegisterUser {
    private final String name;
    private final String email;
    private final String passwd;
    private final String address;
    private final int nif;
    private Point pos;

    public RegisterUser(String name, String email, String passwd, String address, int nif, Point pos) {
        this.email = email;
        this.passwd = passwd;
        this.name = name;
        this.address = address;
        this.nif = nif;
        this.pos = pos;
    }

    public RegisterUser(String name, String email, String passwd, String address, int nif) {
        this.email = email;
        this.passwd = passwd;
        this.name = name;
        this.address = address;
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getNif() {
        return nif;
    }

    public Point getPos() {
        return pos;
    }
}
