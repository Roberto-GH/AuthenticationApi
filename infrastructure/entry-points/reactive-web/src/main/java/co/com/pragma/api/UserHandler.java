package co.com.pragma.api;

import co.com.pragma.api.constans.AuthenticationWebKeys;
import co.com.pragma.api.dto.CreateUserDto;
import co.com.pragma.api.dto.LoginDto;
import co.com.pragma.api.mapper.UserDtoMapper;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.exception.ErrorEnum;
import co.com.pragma.model.user.exception.UserException;
import co.com.pragma.usecase.user.adapters.UserControllerUseCase;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

  private final UserControllerUseCase userControllerUseCase;
  private final UserDtoMapper userDtoMapper;
  private final PasswordEncoder passwordEncoder;
  private final TransactionalOperator transactionalOperator;

  public UserHandler(UserControllerUseCase userControllerUseCase, UserDtoMapper userDtoMapper, PasswordEncoder passwordEncoder, TransactionalOperator transactionalOperator) {
    this.userControllerUseCase = userControllerUseCase;
    this.userDtoMapper = userDtoMapper;
    this.passwordEncoder = passwordEncoder;
    this.transactionalOperator = transactionalOperator;
  }

  @PreAuthorize("hasRole('ADMIN')")
  public Mono<ServerResponse> listenSaveUser(ServerRequest serverRequest) {
    return serverRequest
      .bodyToMono(CreateUserDto.class)
      .switchIfEmpty(Mono.error(new UserException(ErrorEnum.INVALID_USER_DATA, AuthenticationWebKeys.ERROR_USER_DATA_REQUIRED)))
      .map(dto -> {
        User.Builder userBuilder = userDtoMapper.toModel(dto);
        userBuilder.password(passwordEncoder.encode(dto.password()));
        return userBuilder.build();
      })
      .flatMap(user -> userControllerUseCase.saveUser(user).as(transactionalOperator::transactional))
      .map(userDtoMapper::toResponseDto)
      .flatMap(reponseUser -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(reponseUser));
  }

  public Mono<ServerResponse> listenLogin(ServerRequest serverRequest) {
    return serverRequest
      .bodyToMono(LoginDto.class)
      .switchIfEmpty(Mono.error(new UserException(ErrorEnum.INVALID_USER_DATA, AuthenticationWebKeys.ERROR_LOGIN_DATA_REQUIRED)))
      .map(userDtoMapper::toUserLogin)
      .flatMap(userControllerUseCase::login)
      .flatMap(token -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(token));
  }

  @PreAuthorize("hasRole('ADMIN')")
  public Mono<ServerResponse> listenFindByEmail(ServerRequest serverRequest) {
    return Mono
      .just(serverRequest.pathVariable(AuthenticationWebKeys.EMAIL))
      .flatMap(userControllerUseCase::findByEmail)
      .map(userDtoMapper::toResponseDto)
      .flatMap(responseUser -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(responseUser))
      .switchIfEmpty(ServerResponse.noContent().build());
  }

}
