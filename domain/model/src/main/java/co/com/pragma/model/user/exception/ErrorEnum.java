package co.com.pragma.model.user.exception;

public enum ErrorEnum {

  AUTHENTICATION_NOT_FOUND("US-001", "Authentication not found.", 404),
  INVALID_USER_DATA("US-002", "Invalid user data.", 400),
  UNAUTHORIZED_ACCESS("AUTH-001", "Unauthorized access.", 401),
  INVALID_TOKEN("AUTH-002", "The provided token is invalid or has expired.", 403),
  INTERNAL_SERVER_ERROR("SYS-001", "Internal server error.", 500);

  private final String code;
  private final String defaultMessage;
  private final int status;

  ErrorEnum(String code, String defaultMessage, int status) {
    this.code = code;
    this.defaultMessage = defaultMessage;
    this.status = status;
  }

  public String getCode() {
    return code;
  }

  public String getDefaultMessage() {
    return defaultMessage;
  }

  public int getStatus() {
    return status;
  }

}
