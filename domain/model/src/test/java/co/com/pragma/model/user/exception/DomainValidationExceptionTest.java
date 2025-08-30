package co.com.pragma.model.user.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainValidationExceptionTest {

  private static final String MENSAJE = "Campo invalido";
  private static final int CODIGO = 400;

  @Test
  void testDomainValidationExceptionWithMessageAndCode() {
    DomainValidationException exception = new DomainValidationException(ErrorEnum.INVALID_USER_DATA, MENSAJE);
    Assertions.assertAll(
      () -> assertEquals(CODIGO, exception.getStatus()),
      () -> assertEquals(MENSAJE, exception.getMessage())
    );
  }

  @Test
  void testDomainValidationExceptionWithOnlyErrorEnum() {
    DomainValidationException exception = new DomainValidationException(ErrorEnum.INVALID_USER_DATA);
    Assertions.assertAll(
      () -> assertEquals(ErrorEnum.INVALID_USER_DATA.getStatus(), exception.getStatus()),
      () -> assertEquals(ErrorEnum.INVALID_USER_DATA.getDefaultMessage(), exception.getMessage())
    );
  }

}