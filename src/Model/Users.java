package Model;

import Exceptions.UserExistsException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

class Users implements Serializable {
    private final Map<String, User> userBase;

    public Users() {
        this.userBase = new HashMap<>();
    }

    public void addUser(User u) throws UserExistsException {
        if(this.userBase.putIfAbsent(u.getEmail(), u.clone()) != null)
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
