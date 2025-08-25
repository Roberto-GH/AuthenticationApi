package co.com.pragma.model.user.exception;

import lombok.Getter;

@Getter
public class UserException extends Exception {

  private final int status;

  public UserException(String message, int status) {
    super(message);
    this.status = status;
  }

}
