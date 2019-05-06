package Model;

import Model.Client;
import Model.Owner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserBase {
    HashMap<String, Client> clientBase;
    HashMap<String, Owner> ownerBase;

    public UserBase() {
        this.clientBase = new HashMap<>();
        this.ownerBase = new HashMap<>();
    }

    /**
     * Adiciona um user ao sistema
     *
     * @param u Model.User a adicionar
     */
    public void addUser(User u) {
        if(u instanceof Client)
            this.clientBase.put(u.getEmail(), ((Client) u).clone());
        if(u instanceof Owner)
            this.ownerBase.put(u.getEmail(), ((Owner) u).clone());
    }

    public List<String> getClientIDS() {
        return new ArrayList<>(this
                .clientBase
                .keySet());
    }
}
