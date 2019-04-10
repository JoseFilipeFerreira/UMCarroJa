import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class Users {
    private String email;
    private String name;
    private String address;
    private LocalDateTime dateOfBirth;

    public Users(String email, String name, String address, LocalDateTime dateOfBirth) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public Users() {
        this.email = new String();
        this.name = new String();
        this.address = new String();
        this.dateOfBirth = LocalDateTime.now();
    }

    public Users(@NotNull Users u) {
        this.email = u.email;
        this.name = u.email;
        this.address = u.address;
        this.dateOfBirth = u.dateOfBirth;
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

    public Users clone() {
        return new Users(this);
    }

}
