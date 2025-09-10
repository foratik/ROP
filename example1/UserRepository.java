package org.example.example1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.example.example1.Models.User;

public class UserRepository {
    private final Set<String> usernames = Collections.synchronizedSet(new HashSet<>());

    public boolean isUsernameTaken(String username) {
        return usernames.contains(username.toLowerCase());
    }

    public User save(User user) {
        if (user == null) throw new IllegalArgumentException("user cannot be null");
        usernames.add(user.username.toLowerCase());
        return user;
    }
}

