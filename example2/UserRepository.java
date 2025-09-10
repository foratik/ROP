package org.example.example2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.example.example2.Models.ErrorInfo;
import org.example.example2.Models.User;
import org.example.example2.Models.UserInput;

public class UserRepository {
    private final Set<String> usernames = Collections.synchronizedSet(new HashSet<>());

    public Result<UserInput, ErrorInfo> ensureUnique(UserInput input) {
        boolean taken = usernames.contains(input.username.toLowerCase());
        if (taken) return Result.err(new ErrorInfo("USERNAME_TAKEN", "username already taken"));
        return Result.ok(input);
    }

    public Result<User, ErrorInfo> save(User user) {
        usernames.add(user.username.toLowerCase());
        return Result.ok(user);
    }
}

