package co.com.pragma.api.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserDtoTest {

    @Test
    void testCreateUserDto() {
        String firstName = "Test";
        String middleName = "";
        String lastName = "User";
        String secondLastName = "";
        String email = "test@example.com";
        String password = "password123";
        Long identityDocument = 123456789L;
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        String address = "123 Test St";
        Long numberPhone = 1234567890L;
        Long baseSalary = 50000L;

        CreateUserDto dto = new CreateUserDto(
            firstName, middleName, lastName, secondLastName, email, password,
            identityDocument, birthdate, address, numberPhone, baseSalary
        );

        assertAll("CreateUserDto properties",
            () -> assertEquals(firstName, dto.firstName()),
            () -> assertEquals(middleName, dto.middleName()),
            () -> assertEquals(lastName, dto.lastName()),
            () -> assertEquals(secondLastName, dto.secondLastName()),
            () -> assertEquals(email, dto.email()),
            () -> assertEquals(password, dto.password()),
            () -> assertEquals(identityDocument, dto.identityDocument()),
            () -> assertEquals(birthdate, dto.birthdate()),
            () -> assertEquals(address, dto.address()),
            () -> assertEquals(numberPhone, dto.numberPhone()),
            () -> assertEquals(baseSalary, dto.baseSalary())
        );
    }
}