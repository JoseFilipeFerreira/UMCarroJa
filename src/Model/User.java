package Model;

import java.io.Serializable;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 8140329421867679989L;
    private final String email;
    private final String passwd;
    private final String name;
    private final String address;
    private final int nif;
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

    public int getRates() {
        return (this.nRatings == 0)? 100 : (this.rating / this.nRatings);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        User user = (User) o;
        return this.nif == user.nif
                && this.rating == user.rating
                && this.nRatings == user.nRatings
                && this.email.equals(user.email)
                && this.passwd.equals(user.passwd)
                && this.name.equals(user.name)
                && this.address.equals(user.address);
    }
}
