package Model;

import Exceptions.InvalidUserException;
import Exceptions.UserExistsException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

class Users implements Serializable {
    private final Map<String, User> userBase;

    Users() {
        this.userBase = new HashMap<>();
    }

    void addUser(User u) throws UserExistsException {
        if(this.userBase.putIfAbsent(u.getEmail(), u.clone()) != null)
            throw new UserExistsException();
    }

    List<String> getClientIDS() {
        return new ArrayList<>(this
                .userBase
                .keySet());
    }

    User getUser(String id) throws InvalidUserException {
        User a = userBase.get(id);
        if(a == null)
            throw new InvalidUserException();
        return a;
    }
}
