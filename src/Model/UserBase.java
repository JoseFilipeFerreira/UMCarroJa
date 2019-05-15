package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class UserBase {
    Map<Integer, User> userBase;

    public UserBase() {
        this.userBase = new HashMap<>();
    }

    public void addUser(User u) throws UserExistsException {
        if(this.userBase.putIfAbsent(u.getNif(), u.clone()) == null)
            throw new UserExistsException();
    }

    public List<Integer> getClientIDS() {
        return new ArrayList<>(this
                .userBase
                .keySet());
    }

    public User getUser(int id) {
        return userBase.get(id);
    }
}
