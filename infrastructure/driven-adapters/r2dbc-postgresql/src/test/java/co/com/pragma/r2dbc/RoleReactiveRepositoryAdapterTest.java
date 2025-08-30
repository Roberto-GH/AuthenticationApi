package co.com.pragma.r2dbc;

import co.com.pragma.model.user.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleReactiveRepositoryAdapterTest {

  private RoleEntity roleEntity;
  private Role role;

  @Mock
  private RoleReactiveRepository repository;
  @Mock
  private ObjectMapper mapper;
  @InjectMocks
  private RoleReactiveRepositoryAdapter adapter;


  @BeforeEach
  void setUp() {
    roleEntity = new RoleEntity(1L, "ADMIN", "Administrator Role");
    role = new Role(1L, "ADMIN", "Administrator Role");
  }

  @Test
  void findById_shouldReturnRoleWhenFound() {
    when(repository.findById(any(Long.class))).thenReturn(Mono.just(roleEntity));
    when(mapper.map(any(RoleEntity.class), any(Class.class))).thenReturn(role);
    Mono<Role> result = adapter.findById(1L);
    StepVerifier.create(result).assertNext(r -> assertAll(() -> assertEquals(role.getId(), r.getId()), () -> assertEquals(role.getName(), r.getName()))).verifyComplete();
  }

  @Test
  void findById_shouldReturnEmptyWhenNotFound() {
    when(repository.findById(any(Long.class))).thenReturn(Mono.empty());
    Mono<Role> result = adapter.findById(1L);
    StepVerifier.create(result).expectComplete().verify();
  }

}