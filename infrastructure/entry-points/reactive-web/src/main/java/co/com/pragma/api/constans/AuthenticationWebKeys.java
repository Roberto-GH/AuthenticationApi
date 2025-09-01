package co.com.pragma.api.constans;

import lombok.Getter;

public class AuthenticationWebKeys {

  private AuthenticationWebKeys() throws InstantiationException {
    throw new InstantiationException("Instances are forbidden");
  }

  public static final String PATH_ROUTE_AUTH = "routes.auth.paths";
  public static final String PATH_ROUTE_USER = "routes.user.paths";
  public static final String POST_METHOD = "POST";
  public static final String GET_METHOD = "GET";
  public static final String STRING_COMA = ",";
  public static final String REGISTER_CORS_PATH = "/**";
  public static final String OPEN_API_TITLE = "Authentication API";
  public static final String OPEN_API_VERSION = "1.0.0";
  public static final String OPEN_API_DESCRIPTION = "API for user authentication and management.";
  public static final String OPEN_API_SECURITY_NAME = "bearerAuth";
  public static final String OPEN_API_SCHEME = "bearer";
  public static final String OPEN_API_BEARER_FORMAT = "JWT";
  public static final String ERROR_ATTRIBUTE_MESSAGE = "message";
  public static final String ERROR_ATTRIBUTE_ERROR_CODE = "error_code";
  public static final String ERROR_ATTRIBUTE_ERROR = "error";
  public static final String ERROR_ATTRIBUTE_PATH = "path";
  public static final String JWT_ERROR_BAD_TOKEN = "Bad token";
  public static final String JWT_ROLES = "roles";
  public static final String JWT_AUTHORITY = "authority";
  public static final String TOKEN = "token";
  public static final String STRING_AUTH = "auth";
  public static final String STRING_SWAGGER = "swagger";
  public static final String STRING_DOCS = "v3/api-docs";
  public static final String STRING_WEBJARS = "webjars";
  public static final String NO_TOKEN = "No token was found";
  public static final String INVALID_TOKEN = "Invalid auth";
  public static final String BEARER = "Bearer ";
  public static final String STRING_BLANK = "";
  public static final String TOKEN_EXPIRED = "token expired";
  public static final String TOKEN_MALFORMED = "token malformed";
  public static final String BAD_SIGNATURE = "bad signature";
  public static final String ILLEGAL_ARGS = "illegal args";
  public static final String SPRING = "spring";
  public static final String USER_BUILDER_TARGED_MAPPER = "userId";
  public static final String ERROR_USER_DATA_REQUIRED = "User data is required";
  public static final String ERROR_LOGIN_DATA_REQUIRED = "Login data is required";
  public static final String OPEN_API_APPLICATION_PATH_LOGIN = "/api/v1/user";
  public static final String OPEN_API_APPLICATION_PATH_USER_BY_EMAIL = "/api/v1/user/{email}";
  public static final String OPEN_API_BEAN_METHOD_LOGIN = "listenSaveUser";
  public static final String OPEN_API_BEAN_METHOD_USER_BY_EMAIL = "listenFindByEmail";
  public static final String OPEN_API_OPERATION_ID_LOGIN = "saveUser";
  public static final String OPEN_API_OPERATION_ID_USER_BY_EMAIL = "listenFindByEmail";
  public static final String OPEN_API_RESPONSE_CODE = "200";
  public static final String OPEN_API_DESCRIPTION_SUCCESS = "Successful operation";
  public static final String OPEN_API_MEDIA_TYPE = "application/json";
  public static final String OPEN_API_APPLICATION_PATH_AUTH = "/auth/v1/login";
  public static final String OPEN_API_BEAN_METHOD_AUTH = "listenLogin";
  public static final String OPEN_API_OPERATION_ID_AUTH = "login";
  public static final String EMAIL = "email";
  public static final String WRITTING_JSON_ERROR = "{\"error\":\"Error writing JSON output\"}";

  public static final String[] ALLOWED_PATHS = new String[]{"/auth/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/webjars/swagger-ui/**"};

  @Getter
  public enum HttpHeadersEnum {
    CSP_HEADER("Content-Security-Policy", "default-src 'self'; frame-ancestors 'self'; form-action 'self'"),
    STS_HEADER("Strict-Transport-Security", "max-age=31536000;"),
    XCTO_HEADER("X-Content-Type-Options", "nosniff"),
    SERVER_HEADER("Server", ""),
    CACHE_CONTROL_HEADER("Cache-Control", "no-store"),
    PRAGMA_HEADER("Pragma", "no-cache"),
    REFERRER_POLICY_HEADER("Referrer-Policy", "strict-origin-when-cross-origin");

    private final String key;
    private final String value;

    HttpHeadersEnum(String key, String value) {
      this.key = key;
      this.value = value;
    }

  }

}
