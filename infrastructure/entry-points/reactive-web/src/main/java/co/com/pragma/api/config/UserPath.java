package co.com.pragma.api.config;

import co.com.pragma.api.constans.AuthenticationWebKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = AuthenticationWebKeys.PATH_ROUTE_USER)
public class UserPath {

  private String signUp;

}
