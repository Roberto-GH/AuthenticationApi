package co.com.pragma.model.user.validation;

import co.com.pragma.model.user.constants.UserModelKeys;
import co.com.pragma.model.user.exception.DomainValidationException;
import co.com.pragma.model.user.exception.ErrorEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordSpecificationTest {

  private final String FIELD_NAME = "password";
  private final PasswordSpecification passwordSpecification = new PasswordSpecification(FIELD_NAME);

  @Test
  void testValidate_NullPassword_ThrowsException() {
    DomainValidationException exception = assertThrows(DomainValidationException.class,
      () -> passwordSpecification.validate(null));
    assert (exception.getErrorCode().equals(ErrorEnum.INVALID_USER_DATA.getCode()));
    assert (exception.getMessage().equals(UserModelKeys.FIELD_NOT_EMPTY + FIELD_NAME));
  }

  @Test
  void testValidate_EmptyPassword_ThrowsException() {
    DomainValidationException exception = assertThrows(DomainValidationException.class,
      () -> passwordSpecification.validate(""));
    assert (exception.getErrorCode().equals(ErrorEnum.INVALID_USER_DATA.getCode()));
    assert (exception.getMessage().equals(UserModelKeys.FIELD_NOT_EMPTY + FIELD_NAME));
  }

  @Test
  void testValidate_InvalidFormatPassword_ThrowsException() {
    DomainValidationException exception = assertThrows(DomainValidationException.class,
      () -> passwordSpecification.validate("short"));
    assert (exception.getErrorCode().equals(ErrorEnum.INVALID_USER_DATA.getCode()));
    assert (exception.getMessage().equals(UserModelKeys.INVALID_FORMAT_PASSWORD + FIELD_NAME));
  }

  @Test
  void testValidate_ValidPassword_DoesNotThrowException() {
    assertDoesNotThrow(() -> passwordSpecification.validate("Password123*"));
  }
}