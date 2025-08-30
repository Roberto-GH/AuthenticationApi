package co.com.pragma.api.constans;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthenticationWebKeysTest {

  @Test
  void testConstructor() {
    assertThrows(InvocationTargetException.class, () -> {
      Constructor<AuthenticationWebKeys> constructor = AuthenticationWebKeys.class.getDeclaredConstructor();
      constructor.setAccessible(true);
      constructor.newInstance();
    });
  }

  @Test
  void testHttpHeadersEnumValues() {
    assertNotNull(AuthenticationWebKeys.HttpHeadersEnum.CSP_HEADER);
    assertNotNull(AuthenticationWebKeys.HttpHeadersEnum.STS_HEADER);
    assertNotNull(AuthenticationWebKeys.HttpHeadersEnum.XCTO_HEADER);
    assertNotNull(AuthenticationWebKeys.HttpHeadersEnum.SERVER_HEADER);
    assertNotNull(AuthenticationWebKeys.HttpHeadersEnum.CACHE_CONTROL_HEADER);
    assertNotNull(AuthenticationWebKeys.HttpHeadersEnum.PRAGMA_HEADER);
    assertNotNull(AuthenticationWebKeys.HttpHeadersEnum.REFERRER_POLICY_HEADER);
    assertNotNull(AuthenticationWebKeys.HttpHeadersEnum.CSP_HEADER.getKey());
    assertNotNull(AuthenticationWebKeys.HttpHeadersEnum.CSP_HEADER.getValue());
  }
}
