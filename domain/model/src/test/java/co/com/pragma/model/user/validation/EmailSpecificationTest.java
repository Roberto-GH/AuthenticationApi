package co.com.pragma.model.user.validation;

import co.com.pragma.model.user.exception.DomainValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailSpecificationTest {

    private static final String FIELD_NAME = "emailField";

    @Test
    void validate_shouldThrowExceptionWhenCandidateIsNull() {
        EmailSpecification specification = new EmailSpecification(FIELD_NAME);
        assertThrows(DomainValidationException.class, () -> specification.validate(null));
    }

    @Test
    void validate_shouldThrowExceptionWhenCandidateIsEmpty() {
        EmailSpecification specification = new EmailSpecification(FIELD_NAME);
        assertThrows(DomainValidationException.class, () -> specification.validate(""));
    }

    @Test
    void validate_shouldThrowExceptionWhenCandidateIsInvalidEmailFormat() {
        EmailSpecification specification = new EmailSpecification(FIELD_NAME);
        assertThrows(DomainValidationException.class, () -> specification.validate("invalid-email"));
    }

    @Test
    void validate_shouldNotThrowExceptionWhenCandidateIsValidEmailFormat() {
        EmailSpecification specification = new EmailSpecification(FIELD_NAME);
        assertDoesNotThrow(() -> specification.validate("test@example.com"));
    }
}
