package org.example.example2;

import org.example.example2.Models.UserInput;

public class Example2Main {
    public static void main(String[] args) {
        UserRepository repo = new UserRepository();
        EmailService email = new EmailService();
        RegistrationService service = new RegistrationService(repo, email);

        Result<?, ?> r1 = service.register(new UserInput("  Bob  ", "bob@example.com "));
        System.out.println("[example2] Result 1: " + r1);

        Result<?, ?> r2 = service.register(new UserInput("bob", "bob@duplicate.com"));
        System.out.println("[example2] Result 2: " + r2);

        Result<?, ?> r3 = service.register(new UserInput("", "bad-email"));
        System.out.println("[example2] Result 3: " + r3);
    }
}

