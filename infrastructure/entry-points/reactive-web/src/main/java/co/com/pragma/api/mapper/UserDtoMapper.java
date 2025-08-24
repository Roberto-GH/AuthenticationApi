package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.CreateUserDto;
import co.com.pragma.api.dto.LoginDto;
import co.com.pragma.api.dto.UserDetailsDto;
import co.com.pragma.api.dto.UserResponseDto;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.UserLogin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

  UserResponseDto toResponseDto(User user);

  User toModel(CreateUserDto createUserDto);

  UserDetailsDto toUserDetails(User user);

  UserLogin toUserLogin(LoginDto loginDto);

}
