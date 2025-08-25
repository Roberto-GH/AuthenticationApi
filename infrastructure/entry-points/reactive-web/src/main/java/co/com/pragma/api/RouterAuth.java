package co.com.pragma.api;

import co.com.pragma.api.config.AuthPath;
import co.com.pragma.api.dto.LoginDto;
import co.com.pragma.model.user.Token;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterAuth {

  private final AuthPath authPath;
  private final UserHandler userHandler;

  public RouterAuth(AuthPath authPath, UserHandler userHandler) {
    this.authPath = authPath;
    this.userHandler = userHandler;
  }

  @RouterOperations({
    @RouterOperation(
      path = "/auth/v1/login",
      method = RequestMethod.POST,
      beanClass = UserHandler.class,
      beanMethod = "listenLogin",
      operation = @Operation(
        operationId = "login", responses = {
        @ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = Token.class)))
      },
        requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = LoginDto.class)))
      ))
  })
  @Bean
  public RouterFunction<ServerResponse> routerFunctionAuth() {
    return route(POST(authPath.getLogin()), userHandler::listenLogin);
  }

}
