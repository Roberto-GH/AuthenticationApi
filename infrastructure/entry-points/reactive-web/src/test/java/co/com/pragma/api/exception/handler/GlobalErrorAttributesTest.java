package co.com.pragma.api.exception.handler;

import co.com.pragma.api.exception.AuthenticationApiException;
import co.com.pragma.model.user.exception.DomainValidationException;
import co.com.pragma.model.user.exception.ErrorEnum;
import co.com.pragma.model.user.exception.UserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class GlobalErrorAttributesTest {

  @Mock
  private ServerRequest request;
  @Mock
  private ErrorAttributeOptions options;

  @InjectMocks
  private GlobalErrorAttributes globalErrorAttributes;

  @Test
  void getErrorAttributes_AuthenticationApiException() {
    AuthenticationApiException apiException = new AuthenticationApiException(ErrorEnum.INVALID_USER_DATA, "Test Exception");
    Mockito.when(request.attribute(DefaultErrorAttributes.class.getName() + ".ERROR")).thenReturn(Optional.of(apiException));
    Mockito.when(request.path()).thenReturn("/test/path");
    Map<String, Object> result = globalErrorAttributes.getErrorAttributes(request, options);
    assertAll(
      () -> Assertions.assertEquals("Test Exception", result.get("message")),
      () -> Assertions.assertEquals("US-002", result.get("error_code")),
      () -> Assertions.assertEquals("Bad Request", result.get("error")),
      () -> Assertions.assertEquals("/test/path", result.get("path"))
    );
  }

  @Test
  void getErrorAttributes_UserException() {
    UserException apiException = new UserException(ErrorEnum.INVALID_USER_DATA, "Test Exception");
    Mockito.when(request.attribute(DefaultErrorAttributes.class.getName() + ".ERROR")).thenReturn(Optional.of(apiException));
    Mockito.when(request.path()).thenReturn("/test/path");
    Map<String, Object> result = globalErrorAttributes.getErrorAttributes(request, options);
    assertAll(
      () -> Assertions.assertEquals("Test Exception", result.get("message")),
      () -> Assertions.assertEquals("US-002", result.get("error_code")),
      () -> Assertions.assertEquals("Bad Request", result.get("error")),
      () -> Assertions.assertEquals("/test/path", result.get("path"))
    );
  }

  @Test
  void getErrorAttributes_DomainValidationException() {
    DomainValidationException apiException = new DomainValidationException(ErrorEnum.INVALID_USER_DATA, "Test Exception");
    Mockito.when(request.attribute(DefaultErrorAttributes.class.getName() + ".ERROR")).thenReturn(Optional.of(apiException));
    Mockito.when(request.path()).thenReturn("/test/path");
    Map<String, Object> result = globalErrorAttributes.getErrorAttributes(request, options);
    assertAll(
      () -> Assertions.assertEquals("Test Exception", result.get("message")),
      () -> Assertions.assertEquals("US-002", result.get("error_code")),
      () -> Assertions.assertEquals("Bad Request", result.get("error")),
      () -> Assertions.assertEquals("/test/path", result.get("path"))
    );
  }

  @Test
  void getErrorAttributes_AuthenticationException() {
    AuthenticationApiException apiException = new AuthenticationApiException (ErrorEnum.INVALID_USER_DATA, "Test Exception");
    Mockito.when(request.attribute(DefaultErrorAttributes.class.getName() + ".ERROR")).thenReturn(Optional.of(apiException));
    Mockito.when(request.path()).thenReturn("/test/path");
    Map<String, Object> result = globalErrorAttributes.getErrorAttributes(request, options);
    assertAll(
      () -> Assertions.assertEquals("Test Exception", result.get("message")),
      () -> Assertions.assertEquals("US-002", result.get("error_code")),
      () -> Assertions.assertEquals("Bad Request", result.get("error")),
      () -> Assertions.assertEquals("/test/path", result.get("path"))
    );
  }

  @Test
  void getErrorAttributes_GenericException() {
    Exception genericException = new RuntimeException("Generic Error");
    Mockito.when(request.attribute(DefaultErrorAttributes.class.getName() + ".ERROR")).thenReturn(Optional.of(genericException));
    Mockito.when(request.path()).thenReturn("/test/path");
    Map<String, Object> result = globalErrorAttributes.getErrorAttributes(request, options);
    assertAll(
      () -> Assertions.assertEquals(ErrorEnum.INTERNAL_SERVER_ERROR.getDefaultMessage(), result.get("message")),
      () -> Assertions.assertEquals(ErrorEnum.INTERNAL_SERVER_ERROR.getCode(), result.get("error_code")),
      () -> Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.get("error")),
      () -> Assertions.assertEquals("/test/path", result.get("path"))
    );
  }

}