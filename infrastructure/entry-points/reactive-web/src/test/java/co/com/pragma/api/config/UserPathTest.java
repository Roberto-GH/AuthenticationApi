package co.com.pragma.api.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserPathTest {

  @Test
  void testApplicationPath() {
    UserPath userPath = new UserPath();
    String path = "/test/path";
    userPath.setSignUp(path);
    assertEquals(path, userPath.getSignUp());
  }

}
