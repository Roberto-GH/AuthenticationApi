package co.com.pragma.config;

import co.com.pragma.model.user.gateways.EncryptUtil;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.usecase.user.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

  @Bean
  public UserUseCase userUseCase(UserRepository userRepository, EncryptUtil encryptUtil) {
    return new UserUseCase(userRepository, encryptUtil);
  }

}
