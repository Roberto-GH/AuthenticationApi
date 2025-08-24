package co.com.pragma.api;

import co.com.pragma.api.dto.CreateUserDto;
import co.com.pragma.api.dto.LoginDto;
import co.com.pragma.api.mapper.UserDtoMapper;
import co.com.pragma.model.user.User;
import co.com.pragma.usecase.user.adapters.UserControllerUseCase;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

  private final UserControllerUseCase userControllerUseCase;
  private final UserDtoMapper userDtoMapper;
  private final PasswordEncoder passwordEncoder;

  public UserHandler(UserControllerUseCase userControllerUseCase, UserDtoMapper userDtoMapper, PasswordEncoder passwordEncoder) {
    this.userControllerUseCase = userControllerUseCase;
    this.userDtoMapper = userDtoMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @PreAuthorize("hasRole('ADMIN')")
  public Mono<ServerResponse> listenSaveUser(ServerRequest serverRequest) {
    return serverRequest
      .bodyToMono(CreateUserDto.class)
      .map(dto -> {
        User user = userDtoMapper.toModel(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRolId(2L);
        return user;
      })
      .flatMap(userControllerUseCase::saveUser)
      .map(userDtoMapper::toResponseDto)
      .flatMap(reponseUser -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(reponseUser));
  }

  public Mono<ServerResponse> listenLogin(ServerRequest serverRequest) {
    return serverRequest
      .bodyToMono(LoginDto.class)
      .map(userDtoMapper::toUserLogin)
      .flatMap(userControllerUseCase::login)
      .flatMap(token -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(token));
  }

}
