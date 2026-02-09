package org.example.service;

import org.example.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> users;

    public UserService() {
        users = new HashMap<>();
        // Usuario admin por defecto
        users.put("admin", new User("admin", "admin123", true));
        // Usuario normal de ejemplo
        users.put("user", new User("user", "user123", false));
    }

    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void registerUser(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password, false));
        }
    }
}
