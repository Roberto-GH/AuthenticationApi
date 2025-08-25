package co.com.pragma.r2dbc;

import co.com.pragma.api.dto.UserDetailsDto;
import co.com.pragma.api.jwt.JwtProvider;
import co.com.pragma.model.user.Token;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.UserLogin;
import co.com.pragma.model.user.exception.UserException;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations<User, UserEntity, UUID, UserReactiveRepository>
  implements UserRepository {

  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;
  private final RoleReactiveRepositoryAdapter roleReactiveRepositoryAdapter;

  public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper, PasswordEncoder passwordEncoder, JwtProvider jwtProvider,
                                       RoleReactiveRepositoryAdapter roleReactiveRepositoryAdapter) {
    super(repository, mapper, entity -> mapper.mapBuilder(entity, User.Builder.class).build());
    this.passwordEncoder = passwordEncoder;
    this.jwtProvider = jwtProvider;
    this.roleReactiveRepositoryAdapter = roleReactiveRepositoryAdapter;
  }

  @Override
  public Mono<User> saveUser(User user) {
    return super.save(user);
  }

  @Override
  public Mono<User> findByEmail(String email) {
    return repository.findByEmail(email)
      .switchIfEmpty(Mono.empty())
      .map(this::toDomain);
  }

  @Override
  public Mono<Token> getToken(UserLogin userLogin) {
    return this
      .findByEmail(userLogin.getEmail())
      .switchIfEmpty(Mono.error(new UserException("User with email " + userLogin.getEmail() + " does not exist", HttpStatus.BAD_REQUEST.value())))
      .filter(user -> passwordEncoder.matches(userLogin.getPassword(), user.getPassword()))
      .switchIfEmpty(Mono.error(new UserException("Invalid credentials", HttpStatus.UNAUTHORIZED.value())))
      .flatMap(user -> roleReactiveRepositoryAdapter
        .findById(user.getRolId())
        .map(role -> UserDetailsDto.builder()
          .username(user.getEmail())
          .password(user.getPassword())
          .roles("ROLE_" + role.getName())
          .build()))
      .map(details -> new Token(jwtProvider.generateToken(details)));
  }

}
