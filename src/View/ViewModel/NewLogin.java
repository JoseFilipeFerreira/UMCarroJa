package View.ViewModel;

public class NewLogin {
    private final String User;
    private final String Password;

    public NewLogin(String user, String password) {
        User = user;
        Password = password;
    }

    public String getUser() {
        return User;
    }

    public String getPassword() {
        return Password;
    }
}
