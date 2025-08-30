package co.com.pragma.model.user.validation;

import co.com.pragma.model.user.exception.DomainValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AmountInRangeSpecificationTest {

    private static final String FIELD_NAME = "testField";
    private static final Long MIN_VALUE = 100L;
    private static final Long MAX_VALUE = 1000L;

    @Test
    void validate_shouldThrowExceptionWhenCandidateIsNull() {
        AmountInRangeSpecification specification = new AmountInRangeSpecification(FIELD_NAME, MIN_VALUE, MAX_VALUE);
        assertThrows(DomainValidationException.class, () -> specification.validate(null));
    }

    @Test
    void validate_shouldThrowExceptionWhenCandidateIsLessThanMin() {
        AmountInRangeSpecification specification = new AmountInRangeSpecification(FIELD_NAME, MIN_VALUE, MAX_VALUE);
        assertThrows(DomainValidationException.class, () -> specification.validate(MIN_VALUE - 1));
    }

    @Test
    void validate_shouldThrowExceptionWhenCandidateIsGreaterThanMax() {
        AmountInRangeSpecification specification = new AmountInRangeSpecification(FIELD_NAME, MIN_VALUE, MAX_VALUE);
        assertThrows(DomainValidationException.class, () -> specification.validate(MAX_VALUE + 1));
    }

    @Test
    void validate_shouldNotThrowExceptionWhenCandidateIsValid() {
        AmountInRangeSpecification specification = new AmountInRangeSpecification(FIELD_NAME, MIN_VALUE, MAX_VALUE);
        assertDoesNotThrow(() -> specification.validate(MIN_VALUE + 1));
        assertDoesNotThrow(() -> specification.validate(MAX_VALUE - 1));
        assertDoesNotThrow(() -> specification.validate(MIN_VALUE));
        assertDoesNotThrow(() -> specification.validate(MAX_VALUE));
    }
}
