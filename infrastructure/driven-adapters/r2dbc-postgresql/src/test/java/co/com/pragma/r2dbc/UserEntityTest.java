package co.com.pragma.r2dbc;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void testUserEntityCreation() {
        UUID userId = UUID.randomUUID();
        String firstName = "John";
        String middleName = "";
        String lastName = "Doe";
        String secondLastName = "";
        String email = "john.doe@example.com";
        String password = "securepassword";
        Long identityDocument = 123456789L;
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        String address = "123 Main St";
        Long numberPhone = 5551234567L;
        Long baseSalary = 50000L;
        Long rolId = 1L;

        UserEntity userEntity = new UserEntity(
            userId, firstName, middleName, lastName, secondLastName, email, password,
            identityDocument, birthdate, address, numberPhone, baseSalary, rolId
        );

        assertAll("UserEntity properties",
            () -> assertEquals(userId, userEntity.getUserId()),
            () -> assertEquals(firstName, userEntity.getFirstName()),
            () -> assertEquals(middleName, userEntity.getMiddleName()),
            () -> assertEquals(lastName, userEntity.getLastName()),
            () -> assertEquals(secondLastName, userEntity.getSecondLastName()),
            () -> assertEquals(email, userEntity.getEmail()),
            () -> assertEquals(password, userEntity.getPassword()),
            () -> assertEquals(identityDocument, userEntity.getIdentityDocument()),
            () -> assertEquals(birthdate, userEntity.getBirthdate()),
            () -> assertEquals(address, userEntity.getAddress()),
            () -> assertEquals(numberPhone, userEntity.getNumberPhone()),
            () -> assertEquals(baseSalary, userEntity.getBaseSalary()),
            () -> assertEquals(rolId, userEntity.getRolId())
        );
    }

    @Test
    void testUserEntitySetters() {
        UserEntity userEntity = new UserEntity();
        UUID newUserId = UUID.randomUUID();
        String newFirstName = "Jane";
        String newMiddleName = "";
        String newLastName = "Smith";
        String newSecondLastName = "";
        String newEmail = "jane.smith@example.com";
        String newPassword = "newsecurepassword";
        Long newIdentityDocument = 987654321L;
        LocalDate newBirthdate = LocalDate.of(1995, 5, 5);
        String newAddress = "456 Oak Ave";
        Long newNumberPhone = 5559876543L;
        Long newBaseSalary = 60000L;
        Long newRolId = 2L;

        userEntity.setUserId(newUserId);
        userEntity.setFirstName(newFirstName);
        userEntity.setMiddleName(newMiddleName);
        userEntity.setLastName(newLastName);
        userEntity.setSecondLastName(newSecondLastName);
        userEntity.setEmail(newEmail);
        userEntity.setPassword(newPassword);
        userEntity.setIdentityDocument(newIdentityDocument);
        userEntity.setBirthdate(newBirthdate);
        userEntity.setAddress(newAddress);
        userEntity.setNumberPhone(newNumberPhone);
        userEntity.setBaseSalary(newBaseSalary);
        userEntity.setRolId(newRolId);

        assertAll("UserEntity setters",
            () -> assertEquals(newUserId, userEntity.getUserId()),
            () -> assertEquals(newFirstName, userEntity.getFirstName()),
            () -> assertEquals(newMiddleName, userEntity.getMiddleName()),
            () -> assertEquals(newLastName, userEntity.getLastName()),
            () -> assertEquals(newSecondLastName, userEntity.getSecondLastName()),
            () -> assertEquals(newEmail, userEntity.getEmail()),
            () -> assertEquals(newPassword, userEntity.getPassword()),
            () -> assertEquals(newIdentityDocument, userEntity.getIdentityDocument()),
            () -> assertEquals(newBirthdate, userEntity.getBirthdate()),
            () -> assertEquals(newAddress, userEntity.getAddress()),
            () -> assertEquals(newNumberPhone, userEntity.getNumberPhone()),
            () -> assertEquals(newBaseSalary, userEntity.getBaseSalary()),
            () -> assertEquals(newRolId, userEntity.getRolId())
        );
    }
}