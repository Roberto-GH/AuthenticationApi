package co.com.pragma.usecase.user;

import co.com.pragma.model.user.Token;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.UserLogin;
import co.com.pragma.model.user.exception.DomainValidationException;
import co.com.pragma.model.user.exception.UserException;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

  private User user;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserUseCase userUseCase;

  @BeforeEach
  public void setUp() {
    user = User.builder()
      .userId(UUID.randomUUID())
      .firstName("John")
      .lastName("Doe")
      .email("john.doe@example.com")
      .password("password")
      .baseSalary(50000L)
      .rolId(1L)
      .build();
  }

  @Test
  public void testSaveUser_Success() {
    when(userRepository.findByEmail(any(String.class))).thenReturn(Mono.empty());
    when(userRepository.saveUser(any(User.class))).thenReturn(Mono.just(user));
    StepVerifier.create(userUseCase.saveUser(user))
      .expectNext(user)
      .verifyComplete();
  }

  @Test
  public void tesFindByEmail_Success() {
    when(userRepository.findByEmail(any(String.class))).thenReturn(Mono.just(user));
    StepVerifier.create(userUseCase.findByEmail("john.doe@example.com"))
      .expectNext(user)
      .verifyComplete();
  }

  @Test
  public void testSaveUser_EmailExists() {
    when(userRepository.findByEmail(any(String.class))).thenReturn(Mono.just(user));
    when(userRepository.saveUser(any(User.class))).thenReturn(Mono.just(user));
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
    UserLogin userLogin = new UserLogin("john.doe@example.com", "password");
    Token token = new Token("some-jwt-token");
    when(userRepository.getToken(any(UserLogin.class))).thenReturn(Mono.just(token));
    StepVerifier.create(userUseCase.login(userLogin))
      .expectNext(token)
      .verifyComplete();
  }

  @Test
  public void testLogin_InvalidEmail() {
    UserLogin userLogin = new UserLogin("invalid-email", "password");
    StepVerifier.create(userUseCase.login(userLogin))
      .expectError(DomainValidationException.class)
      .verify();
  }
}
