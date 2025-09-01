package co.com.pragma.model.user.constants;

import java.util.regex.Pattern;

public class UserModelKeys {

  private UserModelKeys() throws InstantiationException {
    throw new InstantiationException("Instances are forbidden");
  }

  public static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
  public static final String FIELD_NOT_NULL = "The field cannot be null. => ";
  public static final String FIELD_NOT_EMPTY = "The field cannot be empty. => ";
  public static final String FIELD_NOT_NULL_OR_EMPTY = "The field cannot be null or empty. => ";
  public static final String FIELD_RANGE = "The filed must be between. => ";
  public static final String INVALID_FORMAT = "The format is not a valid of the field. => ";
  public static final String CODE_ERROR_US_001 = "US-001";
  public static final String CODE_ERROR_US_002 = "US-002";
  public static final String CODE_ERROR_AUTH_001 = "AUTH-001";
  public static final String CODE_ERROR_AUTH_002 = "AUTH-002";
  public static final String CODE_ERROR_AUTH_003 = "AUTH-003";
  public static final String CODE_ERROR_SYS_001 = "SYS-001";
  public static final String DEFAULT_MESSAGE_US_OO1 = "Authentication not found.";
  public static final String DEFAULT_MESSAGE_US_002 = "Invalid user data.";
  public static final String DEFAULT_MESSAGE_AUTH_001 = "Unauthorized access.";
  public static final String DEFAULT_MESSAGE_AUTH_002 = "The provided token is invalid or has expired.";
  public static final String DEFAULT_MESSAGE_AUTH_003 = "Access denied.";
  public static final String DEFAULT_MESSAGE_SYS_001 = "Internal server error.";
  public static final int STATUS_400 = 400;
  public static final int STATUS_401 = 401;
  public static final int STATUS_403 = 403;
  public static final int STATUS_404 = 404;
  public static final int STATUS_500 = 500;

}
