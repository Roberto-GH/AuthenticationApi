package co.com.pragma.model.user.gateways;

import reactor.core.publisher.Mono;

public interface EncryptUtil {

  Mono<String> encrypt(String value);

  Mono<Boolean> matches(String rawValue, String encryptedValue);

}
