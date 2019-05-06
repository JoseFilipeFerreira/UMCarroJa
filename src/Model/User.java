package Model;

import java.time.LocalDateTime;

public abstract class User {
    private String email;
    private String name;
    private String address;
    private LocalDateTime dateOfBirth;

    public User(String email, String name, String address, LocalDateTime dateOfBirth) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {
        this.email = "";
        this.name = "";
        this.address = "";
        this.dateOfBirth = LocalDateTime.now();
    }

    public User(User u) {
        this.email = u.getEmail();
        this.name = u.getName();
        this.address = u.getAddress();
        this.dateOfBirth = u.getDateOfBirth();
   }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDateTime getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract User clone();

}
