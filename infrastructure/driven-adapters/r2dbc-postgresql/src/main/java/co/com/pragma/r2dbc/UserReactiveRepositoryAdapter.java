package co.com.pragma.r2dbc;

import co.com.pragma.api.dto.UserDetailsDto;
import co.com.pragma.api.jwt.JwtProvider;
import co.com.pragma.model.user.Token;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.exception.ErrorEnum;
import co.com.pragma.model.user.exception.UserException;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.r2dbc.constants.PostgreSQLKeys;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations<User, UserEntity, UUID, UserReactiveRepository>
  implements UserRepository {

  private final JwtProvider jwtProvider;
  private final RoleReactiveRepositoryAdapter roleReactiveRepositoryAdapter;

  public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper, JwtProvider jwtProvider,
                                       RoleReactiveRepositoryAdapter roleReactiveRepositoryAdapter) {
    super(repository, mapper, entity -> mapper.mapBuilder(entity, User.Builder.class).build());
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
  public Mono<Token> getToken(User user) {
    return roleReactiveRepositoryAdapter
      .findById(user.getRolId())
      .switchIfEmpty(Mono.error(new UserException(ErrorEnum.INVALID_USER_DATA, PostgreSQLKeys.ROLE_NOT_FOUND)))
      .map(role -> UserDetailsDto
        .builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .roles(PostgreSQLKeys.ROLE_PREFIX + role.getName())
        .build())
      .map(details -> new Token(jwtProvider.generateToken(details)));
  }

}
