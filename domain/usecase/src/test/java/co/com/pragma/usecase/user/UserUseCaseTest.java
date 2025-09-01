package co.com.pragma.usecase.user;

import co.com.pragma.model.user.Token;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.UserLogin;
import co.com.pragma.model.user.exception.DomainValidationException;
import co.com.pragma.model.user.exception.UserException;
import co.com.pragma.model.user.gateways.EncryptUtil;
import co.com.pragma.model.user.gateways.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

  private User user;

  @Mock
  private UserRepository userRepository;

  @Mock
  private EncryptUtil encryptUtil;

  @InjectMocks
  private UserUseCase userUseCase;

  @BeforeEach
  public void setUp() {
    user = User.builder()
      .userId(UUID.randomUUID())
      .firstName("John")
      .lastName("Doe")
      .email("john.doe@example.com")
      .password("Password123*")
      .baseSalary(50000L)
      .rolId(1L)
      .build();
  }

  @Test
  public void testSaveUser_Success() {
    when(userRepository.findByEmail(anyString())).thenReturn(Mono.empty());
    when(encryptUtil.encrypt(anyString())).thenReturn(Mono.just("encryptedPassword"));
    when(userRepository.saveUser(any(User.class))).thenReturn(Mono.just(user));
    StepVerifier.create(userUseCase.saveUser(user))
      .expectNext(user)
      .verifyComplete();
  }

  @Test
  public void tesFindByEmail_Success() {
    when(userRepository.findByEmail(anyString())).thenReturn(Mono.just(user));
    StepVerifier.create(userUseCase.findByEmail("john.doe@example.com"))
      .expectNext(user)
      .verifyComplete();
  }

  @Test
  public void testSaveUser_EmailExists() {
    User user = User.builder()
      .firstName("John")
      .lastName("Doe")
      .email("john.doe@example.com")
      .password("Password123_")
      .baseSalary(50000L)
      .build();
    when(userRepository.findByEmail(anyString())).thenReturn(Mono.just(user));
    StepVerifier.create(userUseCase.saveUser(user))
      .expectError(UserException.class)
      .verify();
  }

  @Test
  public void testSaveUser_InvalidFirstName() {
    User user = User.builder()
      .userId(UUID.randomUUID())
      .firstName("")
      .lastName("Doe")
      .email("john.doe@example.com")
      .password("Password123*")
      .baseSalary(50000L)
      .rolId(1L)
      .build();
    StepVerifier.create(userUseCase.saveUser(user))
      .expectError(DomainValidationException.class)
      .verify();
  }

  @Test
  public void testSaveUser_InvalidPassword() {
    User user = User.builder()
      .userId(UUID.randomUUID())
      .firstName("John")
      .lastName("Doe")
      .email("john.doe@example.com")
      .password("password")
      .baseSalary(50000L)
      .rolId(1L)
      .build();
    StepVerifier.create(userUseCase.saveUser(user))
      .expectError(DomainValidationException.class)
      .verify();
  }

  @Test
  public void testLogin_Success() {
    UserLogin userLogin = new UserLogin("john.doe@example.com", "Password123*");
    Token token = new Token("some-jwt-token");
    user.setPassword("encryptedPassword");
    when(userRepository.findByEmail(anyString())).thenReturn(Mono.just(user));
    when(encryptUtil.matches(anyString(), anyString())).thenReturn(Mono.just(true));
    when(userRepository.getToken(any(User.class))).thenReturn(Mono.just(token));
    StepVerifier.create(userUseCase.login(userLogin))
      .expectNext(token)
      .verifyComplete();
  }

  @Test
  public void testLogin_UserNotFound() {
    UserLogin userLogin = new UserLogin("john.doe@example.com", "Password123*");
    when(userRepository.findByEmail(anyString())).thenReturn(Mono.empty());
    StepVerifier.create(userUseCase.login(userLogin))
      .expectError(UserException.class)
      .verify();
  }

  @Test
  public void testLogin_InvalidPassword() {
    UserLogin userLogin = new UserLogin("john.doe@example.com", "Password123*");
    user.setPassword("encryptedPassword");
    when(userRepository.findByEmail(anyString())).thenReturn(Mono.just(user));
    when(encryptUtil.matches(anyString(), anyString())).thenReturn(Mono.just(false));
    StepVerifier.create(userUseCase.login(userLogin))
      .expectError(UserException.class)
      .verify();
  }

  @Test
  public void testLogin_InvalidEmail() {
    UserLogin userLogin = new UserLogin("invalid-email", "password");
    StepVerifier.create(userUseCase.login(userLogin))
      .expectError(DomainValidationException.class)
      .verify();
  }

  @Test
  public void testAssertUserEmailNotExists() {
    when(userRepository.findByEmail(anyString())).thenReturn(Mono.just(user));
    StepVerifier.create(userUseCase.assertUserEmailNotExists("john.doe@example.com"))
      .expectError(UserException.class)
      .verify();
  }
}
