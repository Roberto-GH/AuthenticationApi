package co.com.pragma.api.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginDtoTest {

    @Test
    void testLoginDto() {
        String email = "test@example.com";
        String password = "testpassword";

        LoginDto dto = new LoginDto(email, password);

        assertAll("LoginDto properties",
            () -> assertEquals(email, dto.email()),
            () -> assertEquals(password, dto.password())
        );
    }
}