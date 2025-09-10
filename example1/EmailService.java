package org.example.example1;

import org.example.example1.Models.User;

public class EmailService {
    public void sendWelcomeEmail(User user) {
        if (user == null) throw new IllegalArgumentException("user cannot be null");
        System.out.println("[example1] Sending welcome email to: " + user.email);
    }
}

