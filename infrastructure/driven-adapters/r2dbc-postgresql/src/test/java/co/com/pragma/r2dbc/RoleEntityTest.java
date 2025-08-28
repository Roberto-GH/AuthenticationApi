package co.com.pragma.r2dbc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleEntityTest {

    @Test
    void testRoleEntityCreation() {
        Long id = 1L;
        String name = "ADMIN";
        String description = "Administrator role";

        RoleEntity roleEntity = new RoleEntity(id, name, description);

        assertAll("RoleEntity properties",
            () -> assertEquals(id, roleEntity.getId()),
            () -> assertEquals(name, roleEntity.getName()),
            () -> assertEquals(description, roleEntity.getDescription())
        );
    }

    @Test
    void testRoleEntitySetters() {
        RoleEntity roleEntity = new RoleEntity();
        Long newId = 2L;
        String newName = "USER";
        String newDescription = "User role";

        roleEntity.setId(newId);
        roleEntity.setName(newName);
        roleEntity.setDescription(newDescription);

        assertAll("RoleEntity setters",
            () -> assertEquals(newId, roleEntity.getId()),
            () -> assertEquals(newName, roleEntity.getName()),
            () -> assertEquals(newDescription, roleEntity.getDescription())
        );
    }
}