package org.example.example1;

import java.util.Objects;
import java.util.regex.Pattern;

import org.example.example1.Models.User;
import org.example.example1.Models.UserInput;

/**
 * Non-railway functional-ish implementation using exceptions and null checks.
 */
public class RegistrationService {
    private static final Pattern EMAIL_RE = Pattern.compile("^[^@\n]+@[^@\n]+\\.[^@\n]+$");

    private final UserRepository userRepository;
    private final EmailService emailService;

    public RegistrationService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.emailService = Objects.requireNonNull(emailService);
    }

    public User register(UserInput input) {
        if (input == null) throw new IllegalArgumentException("input cannot be null");

        // Step 1: sanitize
        String username = sanitize(input.username);
        String email = sanitize(input.email);

        // Step 2: validate
        if (username.isEmpty()) throw new IllegalArgumentException("username is required");
        if (!EMAIL_RE.matcher(email).matches()) throw new IllegalArgumentException("invalid email format");
        if (userRepository.isUsernameTaken(username)) throw new IllegalStateException("username already taken");

        // Step 3: create user
        User user = new User(username, email);

        // Step 4: persist
        userRepository.save(user);

        // Step 5: side effect
        try {
            emailService.sendWelcomeEmail(user);
        } catch (RuntimeException ex) {
            // swallow for demo purposes
            System.err.println("[example1] Failed to send email: " + ex.getMessage());
        }

        return user;
    }

    private String sanitize(String s) {
        if (s == null) return "";
        return s.trim();
    }
}

