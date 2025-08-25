package co.com.pragma.api;

import co.com.pragma.api.config.UserPath;
import co.com.pragma.api.dto.CreateUserDto;
import co.com.pragma.api.dto.UserResponseDto;
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
public class RouterUser {

  private final UserPath userPath;
  private final UserHandler userHandler;

  public RouterUser(UserPath userPath, UserHandler userHandler) {
    this.userPath = userPath;
    this.userHandler = userHandler;
  }

  @RouterOperations({
    @RouterOperation(
      path = "/api/v1/user",
      method = RequestMethod.POST,
      beanClass = UserHandler.class,
      beanMethod = "listenSaveUser",
      operation = @Operation(
        operationId = "saveUser", responses = {
          @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class)))
        },
        requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = CreateUserDto.class)))
      ))
  })
  @Bean
  public RouterFunction<ServerResponse> routerFunctionUser() {
    return route(POST(userPath.getSignUp()), userHandler::listenSaveUser);
  }

}
