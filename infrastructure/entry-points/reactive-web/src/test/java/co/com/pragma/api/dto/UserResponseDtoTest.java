package co.com.pragma.api.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseDtoTest {

    @Test
    void testUserResponseDto() {
        String firstName = "Test";
        String middleName = "";
        String lastName = "User";
        String secondLastName = "";
        String email = "test@example.com";
        Long identityDocument = 123456789L;
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        String address = "123 Test St";
        Long numberPhone = 1234567890L;
        Long baseSalary = 50000L;

        UserResponseDto dto = new UserResponseDto(
            firstName, middleName, lastName, secondLastName, email,
            identityDocument, birthdate, address, numberPhone, baseSalary
        );

        assertAll("UserResponseDto properties",
            () -> assertEquals(firstName, dto.firstName()),
            () -> assertEquals(middleName, dto.middleName()),
            () -> assertEquals(lastName, dto.lastName()),
            () -> assertEquals(secondLastName, dto.secondLastName()),
            () -> assertEquals(email, dto.email()),
            () -> assertEquals(identityDocument, dto.identityDocument()),
            () -> assertEquals(birthdate, dto.birthdate()),
            () -> assertEquals(address, dto.address()),
            () -> assertEquals(numberPhone, dto.numberPhone()),
            () -> assertEquals(baseSalary, dto.baseSalary())
        );
    }
}