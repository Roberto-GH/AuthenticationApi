package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.CreateUserDto;
import co.com.pragma.api.dto.LoginDto;
import co.com.pragma.api.dto.UserResponseDto;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.UserLogin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoMapperTest {

  private UserDtoMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = Mappers.getMapper(UserDtoMapper.class);
  }

  @Test
  void toResponseDto_shouldMapApplicationToApplicationResponseDto() {
    User application = User
      .builder()
      .email("email@email.com")
      .build();
    UserResponseDto dto = mapper.toResponseDto(application);
    assertNotNull(dto);
    assertAll(
      () -> assertEquals(application.getEmail(), dto.email())
    );
  }

  @Test
  void toUserLogin_shouldMapLoginDtoToUserLogin() {
    LoginDto login = new LoginDto("email@email.com", "123456");
    UserLogin dto = mapper.toUserLogin(login);
    assertNotNull(dto);
    assertAll(
      () -> assertEquals(login.email(), dto.getEmail())
    );
  }

  @Test
  void toUserLogin_shouldMapLoginDtoToNull() {
    UserLogin dto = mapper.toUserLogin(null);
    assertNull(dto);
  }

  @Test
  void toBuilder_shouldMapCreateApplicationDtoToApplicationBuilder() {
    CreateUserDto createDto = new CreateUserDto(
      "firstName",
      "middleName",
      "lastName",
      "secondLastName",
      "email",
      "password",
      123456L,
      LocalDate.now(),
      "address",
      123456L,
      123456L
    );
    User.Builder builder = mapper.toBuilder(createDto);
    assertNotNull(builder);
    User builtApplication = builder.build();
    assertAll(
      () -> assertEquals(createDto.email(), builtApplication.getEmail())
    );
  }

  @Test
  void toModel_shouldMapCreateApplicationDtoToApplicationBuilder() {
    CreateUserDto createDto = new CreateUserDto(
      "firstName",
      "middleName",
      "lastName",
      "secondLastName",
      "email",
      "password",
      123456L,
      LocalDate.now(),
      "address",
      123456L,
      123456L
    );
    User.Builder builder = mapper.toModel(createDto);
    assertNotNull(builder);
    User builtApplication = builder.build();
    assertAll(
      () -> assertEquals(createDto.email(), builtApplication.getEmail())
    );
  }

  @Test
  void toModel_shouldReturnNullWhenDtoIsNull() {
    User.Builder builder = mapper.toModel(null);
    assertNull(builder);
  }

  @Test
  void toModel_shouldReturnNullWhenResponsIsNull() {
    UserResponseDto builder = mapper.toResponseDto(null);
    assertNull(builder);
  }

  @Test
  void toModel_shouldReturnNullWhenBuilderIsNull() {
    User.Builder builder = mapper.toBuilder(null);
    assertNull(builder);
  }

  @Test
  void testToUserLogin() {
    LoginDto loginDto = new LoginDto("test@example.com", "password");
    UserLogin userLogin = mapper.toUserLogin(loginDto);
    assertNotNull(userLogin);
    assertAll(
      () -> assertEquals(loginDto.email(), userLogin.getEmail()),
      () -> assertEquals(loginDto.password(), userLogin.getPassword())
    );
  }

}
