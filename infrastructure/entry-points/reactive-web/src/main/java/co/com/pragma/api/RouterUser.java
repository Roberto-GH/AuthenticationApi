package co.com.pragma.api;

import co.com.pragma.api.config.UserPath;
import co.com.pragma.api.constans.AuthenticationWebKeys;
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

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterUser {

  private final UserPath userPath;

  public RouterUser(UserPath userPath) {
    this.userPath = userPath;
  }

  @RouterOperations({
    @RouterOperation(
      path = AuthenticationWebKeys.OPEN_API_APPLICATION_PATH_USER,
      method = RequestMethod.POST,
      beanClass = UserHandler.class,
      beanMethod = AuthenticationWebKeys.OPEN_API_BEAN_METHOD_USER,
      operation = @Operation(
        operationId = AuthenticationWebKeys.OPEN_API_OPERATION_ID_USER, responses = {
        @ApiResponse(
          responseCode = AuthenticationWebKeys.OPEN_API_RESPONSE_CODE,
          description = AuthenticationWebKeys.OPEN_API_DESCRIPTION_SUCCESS,
          content = @Content(mediaType = AuthenticationWebKeys.OPEN_API_MEDIA_TYPE, schema = @Schema(implementation = UserResponseDto.class)))
      },
        requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = CreateUserDto.class)))
      )
    ),
    @RouterOperation(
      path = AuthenticationWebKeys.OPEN_API_APPLICATION_PATH_USER_BY_EMAIL,
      method = RequestMethod.GET,
      beanClass = UserHandler.class,
      beanMethod = AuthenticationWebKeys.OPEN_API_BEAN_METHOD_USER_BY_EMAIL,
      operation = @Operation(operationId = AuthenticationWebKeys.OPEN_API_OPERATION_ID_USER_BY_EMAIL, responses = {
        @ApiResponse(
          responseCode = AuthenticationWebKeys.OPEN_API_RESPONSE_CODE,
          description = AuthenticationWebKeys.OPEN_API_DESCRIPTION_SUCCESS,
          content = @Content(mediaType = AuthenticationWebKeys.OPEN_API_MEDIA_TYPE, schema = @Schema(implementation = UserResponseDto.class)))
      })
    )
  })
  @Bean
  public RouterFunction<ServerResponse> routerFunctionUser(UserHandler userHandler) {
    return route(POST(userPath.getSignUp()), userHandler::listenSaveUser)
      .andRoute(GET(userPath.getFindByEmail()), userHandler::listenFindByEmail);
  }

}
