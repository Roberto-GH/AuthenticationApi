package co.com.pragma.r2dbc.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ReactiveAdapterOperationsTest {

  private DummyRepository repository;
  private ObjectMapper mapper;
  private ReactiveAdapterOperations<DummyEntity, DummyData, String, DummyRepository> operations;

  record DummyEntity(String id, String name) {
    public static DummyEntity toEntity(DummyData data) {
      return new DummyEntity(data.id(), data.name());
    }
  }
  record DummyData(String id, String name) {}
  interface DummyRepository extends ReactiveCrudRepository<DummyData, String>, ReactiveQueryByExampleExecutor<DummyData> {}

  @BeforeEach
  void setUp() {
    repository = Mockito.mock(DummyRepository.class);
    mapper = Mockito.mock(ObjectMapper.class);
    operations = new ReactiveAdapterOperations<>(repository, mapper, DummyEntity::toEntity) {};
  }

  @Test
  void save() {
    DummyEntity entity = new DummyEntity("1", "test");
    DummyData data = new DummyData("1", "test");
    when(mapper.map(entity, DummyData.class)).thenReturn(data);
    when(repository.save(data)).thenReturn(Mono.just(data));
    StepVerifier.create(operations.save(entity)).expectNext(entity).verifyComplete();
  }

  @Test
  void saveAllEntities() {
    DummyEntity entity1 = new DummyEntity("1", "test1");
    DummyEntity entity2 = new DummyEntity("2", "test2");
    DummyData data1 = new DummyData("1", "test1");
    DummyData data2 = new DummyData("2", "test2");
    when(mapper.map(entity1, DummyData.class)).thenReturn(data1);
    when(mapper.map(entity2, DummyData.class)).thenReturn(data2);
    when(repository.saveAll(any(Flux.class))).thenReturn(Flux.just(data1, data2));
    StepVerifier.create(operations.saveAllEntities(Flux.just(entity1, entity2))).expectNext(entity1, entity2).verifyComplete();
  }

  @Test
  void findById() {
    DummyData data = new DummyData("1", "test");
    DummyEntity entity = new DummyEntity("1", "test");
    when(repository.findById("1")).thenReturn(Mono.just(data));
    StepVerifier.create(operations.findById("1")).expectNext(entity).verifyComplete();
  }

  @Test
  void findByExample() {
    DummyEntity entity = new DummyEntity("1", "test");
    DummyData data = new DummyData("1", "test");
    when(mapper.map(entity, DummyData.class)).thenReturn(data);
    when(repository.findAll(any())).thenReturn(Flux.just(data));
    StepVerifier.create(operations.findByExample(entity)).expectNext(entity).verifyComplete();
  }

  @Test
  void findAll() {
    DummyData data1 = new DummyData("1", "test1");
    DummyData data2 = new DummyData("2", "test2");
    DummyEntity entity1 = new DummyEntity("1", "test1");
    DummyEntity entity2 = new DummyEntity("2", "test2");
    when(repository.findAll()).thenReturn(Flux.just(data1, data2));
    StepVerifier.create(operations.findAll()).expectNext(entity1, entity2).verifyComplete();
  }

  @Test
  void toDomain() {
    DummyData data = new DummyData("1", "test");
    DummyEntity entity = new DummyEntity("1", "test");
    assert operations.toDomain(data).equals(entity);
    assert operations.toDomain(null) == null;
  }

}
