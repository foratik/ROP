package org.example.example2;

import java.util.regex.Pattern;

import org.example.example2.Models.ErrorInfo;
import org.example.example2.Models.UserInput;

public final class Validators {
    private static final Pattern EMAIL_RE = Pattern.compile("^[^@\n]+@[^@\n]+\\.[^@\n]+$");

    public static Result<UserInput, ErrorInfo> sanitize(UserInput input) {
        if (input == null) return Result.err(new ErrorInfo("INVALID_INPUT", "input cannot be null"));
        String username = input.username == null ? "" : input.username.trim();
        String email = input.email == null ? "" : input.email.trim();
        return Result.ok(new UserInput(username, email));
    }

    public static Result<UserInput, ErrorInfo> validateRequired(UserInput input) {
        if (input.username.isEmpty()) return Result.err(new ErrorInfo("USERNAME_REQUIRED", "username is required"));
        if (input.email.isEmpty()) return Result.err(new ErrorInfo("EMAIL_REQUIRED", "email is required"));
        return Result.ok(input);
    }

    public static Result<UserInput, ErrorInfo> validateEmail(UserInput input) {
        if (!EMAIL_RE.matcher(input.email).matches()) {
            return Result.err(new ErrorInfo("EMAIL_INVALID", "invalid email format"));
        }
        return Result.ok(input);
    }

    private Validators() {}
}

