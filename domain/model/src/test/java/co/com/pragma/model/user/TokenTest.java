package co.com.pragma.model.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

    @Test
    void testTokenCreation() {
        String tokenValue = "someTokenValue";

        Token token = Token.builder()
            .token(tokenValue)
            .build();

        assertAll("Token properties",
            () -> assertEquals(tokenValue, token.getToken())
        );
    }

    @Test
    void testTokenSetters() {
        Token token = Token.builder().build();
        String newTokenValue = "newTokenValue";

        token.setToken(newTokenValue);

        assertAll("Token setters",
            () -> assertEquals(newTokenValue, token.getToken())
        );
    }
}