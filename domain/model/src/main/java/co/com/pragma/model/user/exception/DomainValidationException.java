package co.com.pragma.model.user.exception;

public class DomainValidationException extends CustomException {

  public DomainValidationException(ErrorEnum errorEnum) {
    super(errorEnum);
  }

  public DomainValidationException(ErrorEnum errorEnum, String customMessage) {
    super(errorEnum, customMessage);
  }

}
