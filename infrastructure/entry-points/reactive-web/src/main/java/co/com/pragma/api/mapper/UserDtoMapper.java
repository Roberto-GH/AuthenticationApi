package co.com.pragma.api.mapper;

import co.com.pragma.api.constans.AuthenticationWebKeys;
import co.com.pragma.api.dto.CreateUserDto;
import co.com.pragma.api.dto.LoginDto;
import co.com.pragma.api.dto.UserResponseDto;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.UserLogin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = AuthenticationWebKeys.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {

  UserLogin toUserLogin(LoginDto loginDto);

  UserResponseDto toResponseDto(User user);

  @Mapping(target = AuthenticationWebKeys.USER_BUILDER_TARGED_MAPPER, ignore = true)
  User.Builder toBuilder(CreateUserDto dto);

  default User.Builder toModel(CreateUserDto dto) {
    if (dto == null)
      return null;
    User.Builder userBuilder = toBuilder(dto);
    userBuilder.rolId(2L);
    return userBuilder;
  }

}
