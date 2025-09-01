package co.com.pragma.r2dbc;

import co.com.pragma.model.user.gateways.EncryptUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class EncrytpUtilImpl implements EncryptUtil {

  private final PasswordEncoder passwordEncoder;

  public EncrytpUtilImpl(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Mono<String> encrypt(String value) {    ;
    return Mono.just(passwordEncoder.encode(value));
  }

  @Override
  public Mono<Boolean> matches(String rawValue, String encryptedValue) {
    return Mono.just(passwordEncoder.matches(rawValue, encryptedValue));
  }

}
