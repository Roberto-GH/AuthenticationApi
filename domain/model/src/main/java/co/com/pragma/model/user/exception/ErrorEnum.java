package co.com.pragma.model.user.exception;

import co.com.pragma.model.user.constants.UserModelKeys;

public enum ErrorEnum {

  AUTHENTICATION_NOT_FOUND(UserModelKeys.CODE_ERROR_US_001, UserModelKeys.DEFAULT_MESSAGE_US_OO1, UserModelKeys.STATUS_404),
  INVALID_USER_DATA(UserModelKeys.CODE_ERROR_US_002, UserModelKeys.DEFAULT_MESSAGE_US_002, UserModelKeys.STATUS_400),
  UNAUTHORIZED_ACCESS(UserModelKeys.CODE_ERROR_AUTH_001, UserModelKeys.DEFAULT_MESSAGE_AUTH_001, UserModelKeys.STATUS_401),
  INVALID_TOKEN(UserModelKeys.CODE_ERROR_AUTH_002, UserModelKeys.DEFAULT_MESSAGE_AUTH_002, UserModelKeys.STATUS_403),
  FORBIDDEN_ACCESS(UserModelKeys.CODE_ERROR_AUTH_003, UserModelKeys.DEFAULT_MESSAGE_AUTH_003, UserModelKeys.STATUS_403),
  INTERNAL_SERVER_ERROR(UserModelKeys.CODE_ERROR_SYS_001, UserModelKeys.DEFAULT_MESSAGE_SYS_001, UserModelKeys.STATUS_500);

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
