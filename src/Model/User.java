package Model;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String email;
    private String passwd;
    private String name;
    private String address;
    private int nif;
    private int rating;
    private int nRatings;

    User(String email, String name, String address, int nif, String passwd) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.nif = nif;
        this.passwd = passwd;
        this.rating = 0;
        this.nRatings = 0;
    }

    User(User u) {
        this.email = u.getEmail();
        this.name = u.getName();
        this.address = u.getAddress();
        this.nif = u.getNif();
        this.passwd = u.getPasswd();
        this.rating = u.getRating();
        this.nRatings = u.getNRatings();
   }

    private int getNRatings() {
        return this.nRatings;
    }

    private int getRating() {
        return this.rating;
    }

    String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getRates() {
        return this.rating / this.nRatings;
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

    void rate(int rating) {
        this.nRatings++;
        this.rating += rating;
    }

    public abstract User clone();

}
