package co.com.pragma.r2dbc.constants;

public class PostgreSQLKeys {

  private PostgreSQLKeys() throws InstantiationException {
    throw new InstantiationException("Instances are forbidden");
  }

  public static final String API_POSTGRESS_CONNECTION_POOL = "api-postgres-connection-pool";
  public static final String VALIDATION_QUERY = "SELECT 1";
  public static final int INITIAL_SIZE = 12;
  public static final int MAX_SIZE = 15;
  public static final int MAX_IDLE_TIME = 30;
  public static final String PATH_CONFIG_R2DBC = "adapters.r2dbc";
  public static final String TABLE_NAME_USERS = "users";
  public static final String COLUMN_NAME_USER_ID = "user_id";
  public static final String COLUMN_NAME_USER_FIRST_NAME = "first_name";
  public static final String COLUMN_NAME_USER_MIDDLE_NAME = "middle_name";
  public static final String COLUMN_NAME_USER_LAST_NAME = "last_name";
  public static final String COLUMN_NAME_USER_SECOND_LAST_NAME = "second_last_name";
  public static final String COLUMN_NAME_USER_IDENTITY_DOCUMENT = "identity_document";
  public static final String COLUMN_NAME_USER_NUMBER_PHONE = "number_phone";
  public static final String COLUMN_NAME_USER_BASE_SALARY = "base_salary";
  public static final String COLUMN_NAME_USER_ROLE_ID = "rol_id";

}
