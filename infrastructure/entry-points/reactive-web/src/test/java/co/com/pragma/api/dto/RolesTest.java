package co.com.pragma.api.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RolesTest {

    @Test
    void testRolesEnumValues() {
        assertAll("Roles enum values",
            () -> assertEquals("ROLE_ADMIN", Roles.ROLE_ADMIN.name()),
            () -> assertEquals("ROLE_USER", Roles.ROLE_USER.name())
        );
    }
}