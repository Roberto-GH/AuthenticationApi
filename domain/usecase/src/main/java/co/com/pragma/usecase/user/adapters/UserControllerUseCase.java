package co.com.pragma.usecase.user.adapters;

import co.com.pragma.model.user.Token;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.UserLogin;
import reactor.core.publisher.Mono;

public interface UserControllerUseCase {

  Mono<User> saveUser(User user);

  Mono<Token> login(UserLogin userLogin);

  Mono<User> findByEmail(String email);

}
