package org.example.example1;

import org.example.example1.Models.User;
import org.example.example1.Models.UserInput;

public class Example1Main {
    public static void main(String[] args) {
        UserRepository repo = new UserRepository();
        EmailService email = new EmailService();
        RegistrationService service = new RegistrationService(repo, email);

        try {
            UserInput input = new UserInput("  Alice  ", "alice@example.com ");
            User user = service.register(input);
            System.out.println("[example1] Registered: " + user);

            // Attempt duplicate to show exception path
            service.register(new UserInput("alice", "alice@duplicate.com"));
        } catch (Exception ex) {
            System.err.println("[example1] Error: " + ex.getMessage());
        }
    }
}

