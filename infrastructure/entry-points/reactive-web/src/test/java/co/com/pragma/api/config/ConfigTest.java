package co.com.pragma.api.config;

import co.com.pragma.api.RouterUser;
import co.com.pragma.api.UserHandler;
import co.com.pragma.api.mapper.UserDtoMapper;
import co.com.pragma.usecase.user.adapters.UserControllerUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.reactive.TransactionalOperator;

@ContextConfiguration(classes = {RouterUser.class, UserHandler.class, UserPath.class})
@WebFluxTest(excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class, ReactiveUserDetailsServiceAutoConfiguration.class})
@AutoConfigureWebTestClient
@Import({CorsConfig.class, SecurityHeadersConfig.class})
@TestPropertySource(properties = {"routes.user.paths.signUp=/api/v1/user"})
@Disabled
class ConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserControllerUseCase userControllerUseCase;
    @MockBean
    private UserDtoMapper userDtoMapper;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private TransactionalOperator transactionalOperator;

    // @Test
    // void corsConfigurationShouldAllowOrigins() {
    //     webTestClient.get()
    //             .uri("/api/usecase/path")
    //             .exchange()
    //             .expectStatus().isOk()
    //             .expectHeader().valueEquals("Content-Security-Policy",
    //                     "default-src 'self'; frame-ancestors 'self'; form-action 'self");
    //             .expectHeader().valueEquals("Strict-Transport-Security", "max-age=31536000;")
    //             .expectHeader().valueEquals("X-Content-Type-Options", "nosniff")
    //             .expectHeader().valueEquals("Server", "")
    //             .expectHeader().valueEquals("Cache-Control", "no-store")
    //             .expectHeader().valueEquals("Pragma", "no-cache")
    //             .expectHeader().valueEquals("Referrer-Policy", "strict-origin-when-cross-origin");
    // }

}
