package co.com.pragma.usecase.user.constants;

public class UserUseCaseKeys {

  private UserUseCaseKeys() throws InstantiationException {
    throw new InstantiationException("Instances are forbidden");
  }

  public static final String USER_VALIDATED_EMAIL = "User already exists with email ";
  public static final String FIRST_NAME_FIELD = "firstName";
  public static final String LAST_NAME_FIELD = "lastName";
  public static final String EMAIL_FIELD = "email";
  public static final String PASSWORD_FIELD = "password";
  public static final String SALARY_RANGE_FIELD = "baseSalary";
  public static final Long SALARY_MIN = 0L;
  public static final Long SALARY_MAX = 15000000L;
  public static final String INVALID_CREDENTIALS = "Invalid credentials";
  public static final String NO_EXIST = "User does not exist with email ";

}
