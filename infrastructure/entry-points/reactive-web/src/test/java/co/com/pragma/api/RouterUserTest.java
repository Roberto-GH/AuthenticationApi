package co.com.pragma.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import co.com.pragma.api.config.UserPath;
import co.com.pragma.api.mapper.UserDtoMapper;
import co.com.pragma.usecase.user.adapters.UserControllerUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterUser.class, UserHandler.class, UserPath.class})
@WebFluxTest(excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class, ReactiveUserDetailsServiceAutoConfiguration.class})
@AutoConfigureWebTestClient
@TestPropertySource(properties = {"routes.user.paths.signUp=/api/v1/user"})
@Disabled
class RouterUserTest {

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

    @Test
    void testListenGETUseCase() {
        webTestClient.get()
                .uri("/api/usecase/path")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEmpty();
                        }
                );
    }

    @Test
    void testListenGETOtherUseCase() {
        webTestClient.get()
                .uri("/api/otherusercase/path")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEmpty();
                        }
                );
    }

    // @Test
    // void testListenPOSTUseCase() {
    //     webTestClient.post()
    //             .uri("/api/usecase/otherpath")
    //             .accept(MediaType.APPLICATION_JSON)
    //             .bodyValue("")
    //             .exchange()
    //             .expectStatus().isOk()
    //             .expectBody(String.class)
    //             .value(userResponse -> {
    //                         Assertions.assertThat(userResponse).isEmpty();
    //                     }
    //             );
    // }
}