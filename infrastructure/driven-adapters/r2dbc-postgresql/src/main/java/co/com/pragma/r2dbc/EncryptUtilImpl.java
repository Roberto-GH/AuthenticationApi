package co.com.pragma.r2dbc;

import co.com.pragma.model.user.gateways.EncryptUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class EncryptUtilImpl implements EncryptUtil {

  private final PasswordEncoder passwordEncoder;

  public EncryptUtilImpl(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Mono<String> encrypt(String value) {
    return Mono.fromCallable(() -> passwordEncoder.encode(value))
      .subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Mono<Boolean> matches(String rawValue, String encryptedValue) {
    return Mono.fromCallable(() -> passwordEncoder.matches(rawValue, encryptedValue))
      .subscribeOn(Schedulers.boundedElastic());
  }

}
