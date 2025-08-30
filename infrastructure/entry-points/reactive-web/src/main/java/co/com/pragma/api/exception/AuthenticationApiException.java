package co.com.pragma.api.exception;

import co.com.pragma.model.user.exception.CustomException;
import co.com.pragma.model.user.exception.ErrorEnum;

public class AuthenticationApiException extends CustomException {

  public AuthenticationApiException(ErrorEnum errorEnum) {
    super(errorEnum);
  }

  public AuthenticationApiException(ErrorEnum errorEnum, String customMessage) {
    super(errorEnum, customMessage);
  }

}
