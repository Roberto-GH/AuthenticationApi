package co.com.pragma.api;

import co.com.pragma.api.config.UserPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouterUserTest {

  private WebTestClient webTestClient;

  @Mock
  private UserHandler handler;
  @Mock
  private UserPath userPath;

  @InjectMocks
  private RouterUser routerRest;

  @BeforeEach
  void setUp() {
    when(userPath.getSignUp()).thenReturn("/api/v1/user");
    when(handler.listenSaveUser(any())).thenReturn(ServerResponse.ok().build());
    RouterFunction<ServerResponse> routerFunction = routerRest.routerFunctionUser(handler);
    webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
  }

  @Test
  void testRouterFunction() {
    webTestClient
      .post()
      .uri("/api/v1/user")
      .exchange()
      .expectStatus()
      .isOk();
  }
}