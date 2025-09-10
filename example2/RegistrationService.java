package org.example.example2;

import java.util.Objects;

import org.example.example2.Models.ErrorInfo;
import org.example.example2.Models.User;
import org.example.example2.Models.UserInput;

/**
 * Railway-oriented implementation of registration using Result chaining.
 */
public class RegistrationService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    public RegistrationService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.emailService = Objects.requireNonNull(emailService);
    }

    public Result<User, ErrorInfo> register(UserInput input) {
        return Validators
                .sanitize(input)
                .flatMap(Validators::validateRequired)
                .flatMap(Validators::validateEmail)
                .flatMap(userRepository::ensureUnique)
                .map(ui -> new User(ui.username, ui.email))
                .flatMap(userRepository::save)
                .flatMap(emailService::sendWelcome)
                .tap(user -> System.out.println("[example2] Registered: " + user))
                .tapError(err -> System.err.println("[example2] Failed: " + err));
    }
}

