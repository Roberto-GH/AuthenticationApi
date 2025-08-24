package co.com.pragma.api;

import co.com.pragma.api.config.AuthPath;
import co.com.pragma.api.config.UserPath;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterAuth {

  private final AuthPath authPath;
  private final UserPath userPath;
  private final UserHandler userHandler;

  public RouterAuth(AuthPath authPath, UserPath userPath, UserHandler userHandler) {
    this.authPath = authPath;
    this.userPath = userPath;
    this.userHandler = userHandler;
  }

  @Bean
  public RouterFunction<ServerResponse> routerFunctionAuth() {
    return route(POST(authPath.getLogin()), userHandler::listenLogin)
      .and(route(POST(userPath.getSignUp()), userHandler::listenSaveUser));
  }

}
