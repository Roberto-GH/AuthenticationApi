package co.com.pragma.model.user.validation;

import co.com.pragma.model.user.exception.DomainValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotEmptySpecificationTest {

    private static final String FIELD_NAME = "testField";

    @Test
    void validate_shouldThrowExceptionWhenCandidateIsNull() {
        NotEmptySpecification specification = new NotEmptySpecification(FIELD_NAME);
        assertThrows(DomainValidationException.class, () -> specification.validate(null));
    }

    @Test
    void validate_shouldThrowExceptionWhenCandidateIsEmpty() {
        NotEmptySpecification specification = new NotEmptySpecification(FIELD_NAME);
        assertThrows(DomainValidationException.class, () -> specification.validate(""));
    }

    @Test
    void validate_shouldThrowExceptionWhenCandidateIsBlank() {
        NotEmptySpecification specification = new NotEmptySpecification(FIELD_NAME);
        assertThrows(DomainValidationException.class, () -> specification.validate("   "));
    }

    @Test
    void validate_shouldNotThrowExceptionWhenCandidateIsValid() {
        NotEmptySpecification specification = new NotEmptySpecification(FIELD_NAME);
        assertDoesNotThrow(() -> specification.validate("valid string"));
    }
}
