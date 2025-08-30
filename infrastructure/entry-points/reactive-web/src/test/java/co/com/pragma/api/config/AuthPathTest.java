package co.com.pragma.api.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthPathTest {

  @Test
  void testApplicationPath() {
    AuthPath authPath = new AuthPath();
    String path = "/test/path";
    authPath.setLogin(path);
    assertEquals(path, authPath.getLogin());
  }

}