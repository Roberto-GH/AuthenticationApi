package co.com.pragma.model.user.gateways;

import co.com.pragma.model.user.Token;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.UserLogin;
import reactor.core.publisher.Mono;

public interface UserRepository {

  Mono<User> saveUser(User user);

  Mono<User> findByEmail(String email);

  Mono<Token> getToken(UserLogin userLogin);

}
