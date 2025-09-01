package co.com.pragma.r2dbc;

import co.com.pragma.api.jwt.JwtProvider;
import co.com.pragma.model.user.Role;
import co.com.pragma.model.user.Token;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.UserLogin;
import co.com.pragma.model.user.exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserReactiveRepositoryAdapterTest {

  private final UUID userId = UUID.randomUUID();
  private UserEntity userEntity;
  private User user;
  private User.Builder builder;
  private final UUID userId2 = UUID.randomUUID();
  private UserEntity userEntity2;
  private User user2;

  @Mock
  private UserReactiveRepository repository;
  @Mock
  private ObjectMapper mapper;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private JwtProvider jwtProvider;
  @Mock
  private RoleReactiveRepositoryAdapter roleReactiveRepositoryAdapter;

  @InjectMocks
  private UserReactiveRepositoryAdapter repositoryAdapter;

  @BeforeEach
  void setUp() {
    userEntity = new UserEntity();
    userEntity.setUserId(userId);
    userEntity.setEmail("test@example.com");
    userEntity.setPassword("encodedPassword");
    userEntity.setRolId(1L);

    user = User
      .builder()
      .userId(userId)
      .email("test@example.com")
      .password("password")
      .rolId(1L)
      .build();
    builder = User
      .builder()
      .userId(userEntity.getUserId());
    userEntity2 = new UserEntity();
    userEntity2.setUserId(userId2);
    user2 = User.builder().userId(userId2).build();
  }

  @Test
  void mustFindValueById() {
    when(repository.findById(userId)).thenReturn(Mono.just(userEntity));
    when(mapper.mapBuilder(userEntity, User.Builder.class)).thenReturn(builder);
    Mono<User> result = repositoryAdapter.findById(userId);
    StepVerifier.create(result)
      .assertNext(app -> assertAll(
        () -> assertEquals(user.getUserId(), app.getUserId())
      ))
      .verifyComplete();
  }

  @Test
  void mustFindAllValues() {
    when(repository.findAll()).thenReturn(Flux.just(userEntity, userEntity2));
    when(mapper.mapBuilder(userEntity, User.Builder.class)).thenReturn(User.builder().userId(userEntity.getUserId()));
    when(mapper.mapBuilder(userEntity2, User.Builder.class)).thenReturn(User.builder().userId(userEntity2.getUserId()));
    Flux<User> result = repositoryAdapter.findAll();
    StepVerifier.create(result)
      .assertNext(
        app -> assertAll(
          () -> assertEquals(user.getUserId(), app.getUserId())))
      .assertNext(
        app -> assertAll(
          () -> assertEquals(user2.getUserId(), app.getUserId())))
      .verifyComplete();
  }

  @Test
  void mustFindByExample() {
    when(mapper.map(user, UserEntity.class)).thenReturn(userEntity);
    when(repository.findAll(any(Example.class))).thenReturn(Flux.just(userEntity));
    when(mapper.mapBuilder(userEntity, User.Builder.class)).thenReturn(User.builder().userId(userEntity.getUserId()));
    Flux<User> result = repositoryAdapter.findByExample(user);
    StepVerifier.create(result)
      .assertNext(app -> assertAll(
        () -> assertEquals(user.getUserId(), app.getUserId())
      ))
      .verifyComplete();
  }

  @Test
  void mustSaveValue() {
    when(mapper.map(user, UserEntity.class)).thenReturn(userEntity);
    when(repository.save(userEntity)).thenReturn(Mono.just(userEntity));
    when(mapper.mapBuilder(userEntity, User.Builder.class)).thenReturn(User.builder().userId(userEntity.getUserId()));
    Mono<User> result = repositoryAdapter.save(user);
    StepVerifier.create(result)
      .assertNext(app -> assertAll(
        () -> assertEquals(user.getUserId(), app.getUserId())
      ))
      .verifyComplete();
  }

  @Test
  void saveUser_shouldCallSuperSave() {
    when(mapper.map(any(), any())).thenReturn(userEntity);
    when(repository.save(any())).thenReturn(Mono.just(userEntity));
    when(mapper.mapBuilder(any(), any())).thenReturn(builder);
    Mono<User> result = repositoryAdapter.saveUser(user);
    StepVerifier.create(result)
      .assertNext(us -> assertAll(
        () -> assertEquals(user.getUserId(), us.getUserId())
      ))
      .verifyComplete();
  }

  @Test
  void findByEmail_shouldReturnUserWhenFound() {
    when(repository.findByEmail(any())).thenReturn(Mono.just(userEntity));
    when(mapper.mapBuilder(any(), any())).thenReturn(builder);
    Mono<User> result = repositoryAdapter.findByEmail(user.getEmail());
    StepVerifier.create(result)
      .assertNext(us -> assertAll(
        () -> assertEquals(user.getUserId(), us.getUserId())
      ))
      .verifyComplete();
  }

  @Test
  void findByEmail_shouldReturnEmptyWhenNotFound() {
    when(repository.findByEmail(user.getEmail())).thenReturn(Mono.empty());

    Mono<User> result = repositoryAdapter.findByEmail(user.getEmail());

    StepVerifier.create(result)
      .expectComplete()
      .verify();
  }

  @Test
  void getToken_shouldReturnTokenOnSuccessfulLogin() {
    Role role = Role.builder().id(1L).name("ADMIN").build();
    Token expectedToken = new Token("generatedToken");
    when(roleReactiveRepositoryAdapter.findById(any())).thenReturn(Mono.just(role));
    when(jwtProvider.generateToken(any())).thenReturn("generatedToken");
    Mono<Token> result = repositoryAdapter.getToken(user);
    StepVerifier.create(result)
      .assertNext(t -> assertAll(
        () -> assertEquals(expectedToken.getToken(), t.getToken())
      ))
      .verifyComplete();
  }

  @Test
  void getToken_shouldThrowExceptionWhenRoleNotFound() {
    when(roleReactiveRepositoryAdapter.findById(any())).thenReturn(Mono.empty());
    Mono<Token> result = repositoryAdapter.getToken(user);
    StepVerifier.create(result)
      .expectError(UserException.class)
      .verify();
  }

}
