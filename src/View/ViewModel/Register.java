package View.ViewModel;

import Utils.Point;

public class Register {
    private String name;
    private String email;
    private String passwd;
    private String address;
    private int nif;

    private Point pos;

    public Register(String name, String email, String passwd, String address, int nif, Point pos) {
        this.email = email;
        this.passwd = passwd;
        this.name = name;
        this.address = address;
        this.nif = nif;
        this.pos = pos;
    }

    public Register(String name, String email, String passwd, String address, int nif) {
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
