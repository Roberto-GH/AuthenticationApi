package co.com.pragma.api.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BeansConfigTest {

  @Test
  void resourcesBeanShouldNotBeNull() {
    BeansConfig beansConfig = new BeansConfig();
    WebProperties.Resources resources = beansConfig.resources();
    assertNotNull(resources);
  }

  

}