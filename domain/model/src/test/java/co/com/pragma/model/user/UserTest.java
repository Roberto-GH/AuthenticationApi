package co.com.pragma.model.user;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation() {
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

        User user = new User.Builder()
            .userId(userId)
            .firstName(firstName)
            .middleName(middleName)
            .lastName(lastName)
            .secondLastName(secondLastName)
            .email(email)
            .password(password)
            .identityDocument(identityDocument)
            .birthdate(birthdate)
            .address(address)
            .numberPhone(numberPhone)
            .baseSalary(baseSalary)
            .rolId(rolId)
            .build();

        assertAll("User properties",
            () -> assertEquals(userId, user.getUserId()),
            () -> assertEquals(firstName, user.getFirstName()),
            () -> assertEquals(middleName, user.getMiddleName()),
            () -> assertEquals(lastName, user.getLastName()),
            () -> assertEquals(secondLastName, user.getSecondLastName()),
            () -> assertEquals(email, user.getEmail()),
            () -> assertEquals(password, user.getPassword()),
            () -> assertEquals(identityDocument, user.getIdentityDocument()),
            () -> assertEquals(birthdate, user.getBirthdate()),
            () -> assertEquals(address, user.getAddress()),
            () -> assertEquals(numberPhone, user.getNumberPhone()),
            () -> assertEquals(baseSalary, user.getBaseSalary()),
            () -> assertEquals(rolId, user.getRolId())
        );
    }
}