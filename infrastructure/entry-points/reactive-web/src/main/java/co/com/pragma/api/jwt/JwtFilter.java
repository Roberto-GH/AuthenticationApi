package co.com.pragma.api.jwt;

import co.com.pragma.api.constans.AuthenticationWebKeys;
import co.com.pragma.api.exception.AuthenticationApiException;
import co.com.pragma.model.user.exception.ErrorEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

@Component
public class JwtFilter implements WebFilter {

  @Override
  @NonNull
  public Mono<Void> filter(ServerWebExchange exchange, @NonNull WebFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    String path = request.getPath().value();
    if (path.contains(AuthenticationWebKeys.STRING_AUTH) || path.contains(AuthenticationWebKeys.STRING_SWAGGER) || path.contains(AuthenticationWebKeys.STRING_DOCS) ||
        path.contains(AuthenticationWebKeys.STRING_WEBJARS))
      return chain.filter(exchange);
    String auth = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    if(auth == null)
      return Mono.error(new AuthenticationApiException(ErrorEnum.INVALID_TOKEN, AuthenticationWebKeys.NO_TOKEN));
    if(!auth.startsWith(AuthenticationWebKeys.BEARER))
      return Mono.error(new AuthenticationApiException(ErrorEnum.INVALID_TOKEN, AuthenticationWebKeys.INVALID_TOKEN));
    String token = auth.replace(AuthenticationWebKeys.BEARER, AuthenticationWebKeys.STRING_BLANK);
    exchange.getAttributes().put(AuthenticationWebKeys.TOKEN, token);
    return chain.filter(exchange);
  }

}
