package co.com.pragma.r2dbc;

import co.com.pragma.model.user.Role;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@Slf4j
public class RoleReactiveRepositoryAdapter extends ReactiveAdapterOperations<Role, RoleEntity, Long, RoleReactiveRepository> {

  public RoleReactiveRepositoryAdapter(RoleReactiveRepository repository, ObjectMapper mapper) {
    super(repository, mapper, entity -> mapper.map(entity, Role.class));
  }

  public Mono<Role> findById(Long id) {
    return super.findById(id);
  }

}
