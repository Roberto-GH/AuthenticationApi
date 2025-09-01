package co.com.pragma.r2dbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EncryptUtilImplTest {

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private EncryptUtilImpl encryptUtil;

  @BeforeEach
  void setUp() {
  }

  @Test
  void testEncrypt_Success() {
    String rawPassword = "password";
    String encodedPassword = "encodedPassword";
    when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);

    StepVerifier.create(encryptUtil.encrypt(rawPassword))
      .expectNext(encodedPassword)
      .verifyComplete();
  }

  @Test
  void testMatches_Success() {
    String rawPassword = "password";
    String encodedPassword = "encodedPassword";
    when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

    StepVerifier.create(encryptUtil.matches(rawPassword, encodedPassword))
      .expectNext(true)
      .verifyComplete();
  }

  @Test
  void testMatches_Failure() {
    String rawPassword = "password";
    String encodedPassword = "encodedPassword";
    when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

    StepVerifier.create(encryptUtil.matches(rawPassword, encodedPassword))
      .expectNext(false)
      .verifyComplete();
  }
}