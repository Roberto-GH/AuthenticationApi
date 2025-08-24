package co.com.pragma.usecase.user;

import co.com.pragma.model.user.Token;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.UserLogin;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.usecase.user.adapters.UserControllerUseCase;
import reactor.core.publisher.Mono;

public class UserUseCase implements UserControllerUseCase {

  private final UserRepository userRepository;

  public UserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Mono<User> saveUser(User user) {//TODO validar non nulls  nombres, apellidos, y salario_base
    return this.assertUserEmailNotExists(user.getEmail())
      .then(userRepository.saveUser(user));
  }

  @Override
  public Mono<Token> login(UserLogin userLogin) {//TODO validar nulos
      return userRepository.getToken(userLogin);
  }

  public Mono<Void> assertUserEmailNotExists(String email) {
    return userRepository.findByEmail(email)
      .flatMap(user -> Mono.error(new Exception("User with email " + email + " already exists")))
      .then();
  }

}
