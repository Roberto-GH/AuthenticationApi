package co.com.pragma.config;

import co.com.pragma.model.user.gateways.EncryptUtil;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.r2dbc.EncryptUtilImpl;
import co.com.pragma.usecase.user.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class UseCasesConfig {

  @Bean
  public UserUseCase userUseCase(UserRepository userRepository, EncryptUtil encryptUtil) {
    return new UserUseCase(userRepository, encryptUtil);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public EncryptUtil encryptUtil(PasswordEncoder passwordEncoder) {
    return new EncryptUtilImpl(passwordEncoder);
  }
}
