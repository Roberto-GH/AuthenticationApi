package co.com.pragma.model.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserLoginTest {

    @Test
    void testUserLoginCreation() {
        String email = "test@example.com";
        String password = "testpassword";

        UserLogin userLogin = UserLogin.builder()
            .email(email)
            .password(password)
            .build();

        assertAll("UserLogin properties",
            () -> assertEquals(email, userLogin.getEmail()),
            () -> assertEquals(password, userLogin.getPassword())
        );
    }

    @Test
    void testUserLoginSetters() {
        UserLogin userLogin = UserLogin.builder().build();
        String newEmail = "new@example.com";
        String newPassword = "newpassword";

        userLogin.setEmail(newEmail);
        userLogin.setPassword(newPassword);

        assertAll("UserLogin setters",
            () -> assertEquals(newEmail, userLogin.getEmail()),
            () -> assertEquals(newPassword, userLogin.getPassword())
        );
    }
}