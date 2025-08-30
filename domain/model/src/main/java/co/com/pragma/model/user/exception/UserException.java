package co.com.pragma.model.user.exception;

import lombok.Getter;

@Getter
public class UserException extends CustomException {

  public UserException(ErrorEnum errorEnum) {
    super(errorEnum);
  }

  public UserException(ErrorEnum errorEnum, String customMessage) {
    super(errorEnum, customMessage);
  }

}
