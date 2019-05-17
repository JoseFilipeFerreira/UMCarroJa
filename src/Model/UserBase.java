package Model;

import Exceptions.UserExistsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class UserBase {
    Map<String, User> userBase;

    public UserBase() {
        this.userBase = new HashMap<>();
    }

    public void addUser(User u) throws UserExistsException {
        if(this.userBase.putIfAbsent(u.getEmail(), u.clone()) == null)
            throw new UserExistsException();
    }

    public List<String> getClientIDS() {
        return new ArrayList<>(this
                .userBase
                .keySet());
    }

    public User getUser(String id) {
        return userBase.get(id);
    }
}
