package co.com.pragma.model.user.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserExceptionTest {

  private static final String mensaje = "Campo invalido";
  private static final int codigo = 400;

  @Test
  void testUserExceptionWithMessageAndCode() {
    UserException exception = new UserException(mensaje, codigo);
    assertEquals(codigo, exception.getStatus());
    Assertions.assertAll(
      () -> assertEquals(codigo, exception.getStatus()),
      () -> assertEquals(mensaje, exception.getMessage())
    );
  }

}
