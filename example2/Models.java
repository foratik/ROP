package org.example.example2;

public final class Models {
    public static final class UserInput {
        public final String username;
        public final String email;

        public UserInput(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }

    public static final class User {
        public final String username;
        public final String email;

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    public static final class ErrorInfo {
        public final String code;
        public final String message;

        public ErrorInfo(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String toString() {
            return code + ": " + message;
        }
    }

    private Models() {}
}

