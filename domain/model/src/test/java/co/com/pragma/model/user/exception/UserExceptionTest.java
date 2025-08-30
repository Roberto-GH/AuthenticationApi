package co.com.pragma.model.user.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserExceptionTest {

  private static final String mensaje = "Campo invalido";
  private static final int codigo = 400;

  @Test
  void testUserExceptionWithMessageAndCode() {
    UserException exception = new UserException(ErrorEnum.INVALID_USER_DATA, mensaje);
    assertEquals(codigo, exception.getStatus());
    Assertions.assertAll(
      () -> assertEquals(codigo, exception.getStatus()),
      () -> assertEquals(mensaje, exception.getMessage())
    );
  }

  @Test
  void testUserExceptionWithOnlyErrorEnum() {
    UserException exception = new UserException(ErrorEnum.INVALID_USER_DATA);
    Assertions.assertAll(
      () -> assertEquals(ErrorEnum.INVALID_USER_DATA.getStatus(), exception.getStatus()),
      () -> assertEquals(ErrorEnum.INVALID_USER_DATA.getDefaultMessage(), exception.getMessage())
    );
  }

}
