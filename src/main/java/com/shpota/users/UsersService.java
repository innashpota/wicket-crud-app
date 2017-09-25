package com.shpota.users;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsersService {
    private Map<Integer, User> users = new HashMap<>();

    {
        users.put(1, new User("Kovalev", "A.", "A."));
        users.put(2, new User("Zebrov", "B.", "K."));
        users.put(3, new User("Antonov", "D.", "I."));
    }

    public void createUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(int id) {
        return users.get(id);
    }

    public void deleteUser(int id) {
        users.remove(id);
    }

    public Collection<User> getUsers() {
        return users.values();
    }
}
