package co.com.pragma.model.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testRoleCreation() {
        Long id = 1L;
        String name = "ADMIN";
        String description = "Administrator role";

        Role role = Role.builder().id(id).name(name).description(description).build();

        assertAll("Role properties",
            () -> assertEquals(id, role.getId()),
            () -> assertEquals(name, role.getName()),
            () -> assertEquals(description, role.getDescription())
        );
    }

    @Test
    void testRoleSetters() {
        Role role = Role.builder().build();
        Long newId = 2L;
        String newName = "USER";
        String newDescription = "User role";

        role.setId(newId);
        role.setName(newName);
        role.setDescription(newDescription);

        assertAll("Role setters",
            () -> assertEquals(newId, role.getId()),
            () -> assertEquals(newName, role.getName()),
            () -> assertEquals(newDescription, role.getDescription())
        );
    }
}