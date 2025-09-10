package org.example.example2;

import org.example.example2.Models.ErrorInfo;
import org.example.example2.Models.User;

public class EmailService {
    public Result<User, ErrorInfo> sendWelcome(User user) {
        try {
            System.out.println("[example2] Sending welcome email to: " + user.email);
            return Result.ok(user);
        } catch (RuntimeException ex) {
            return Result.err(new Models.ErrorInfo("EMAIL_FAILED", ex.getMessage()));
        }
    }
}

