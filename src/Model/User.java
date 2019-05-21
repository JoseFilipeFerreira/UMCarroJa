package Model;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String email;
    private String passwd;
    private String name;
    private String address;
    private int nif;

    User(String email, String name, String address, int nif, String passwd) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.nif = nif;
        this.passwd = passwd;
    }

    User() {
        this.nif = 0;
        this.email = "";
        this.name = "";
        this.address = "";
    }

    User(User u) {
        this.email = u.getEmail();
        this.name = u.getName();
        this.address = u.getAddress();
        this.nif = u.getNif();
        this.passwd = u.getPasswd();
   }

    String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    private int getNif() {
        return this.nif;
    }

    private String getName() {
        return this.name;
    }

    private String getAddress() {
        return this.address;
    }

    String getEmail() {
        return this.email;
    }

    public abstract User clone();

}
