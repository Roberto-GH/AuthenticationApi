package co.com.pragma.api;

import co.com.pragma.api.config.AuthPath;
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
class RouterAuthTest {

  private WebTestClient webTestClient;

  @Mock
  private UserHandler handler;
  @Mock
  private AuthPath authPath;

  @InjectMocks
  private RouterAuth routerAuth;

  @BeforeEach
  void setUp() {
    when(authPath.getLogin()).thenReturn("/auth/v1/login");
    when(handler.listenLogin(any())).thenReturn(ServerResponse.ok().build());
    RouterFunction<ServerResponse> routerFunction = routerAuth.routerFunctionAuth(handler);
    webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
  }

  @Test
  void testRouterFunction() {
    webTestClient
      .post()
      .uri("/auth/v1/login")
      .exchange()
      .expectStatus()
      .isOk();
  }
}
