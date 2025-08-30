package co.com.pragma.api.jwt;

import co.com.pragma.api.constans.AuthenticationWebKeys;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class JwtProvider {

  private static final Logger LOG = Logger.getLogger(JwtProvider.class.getName());

  private final String secret;
  private final Integer expiration;

  public JwtProvider(
          @Value("${jwt.secret}") String secret,
          @Value("${jwt.expiration}") Integer expiration
  ) {
    this.secret = secret;
    this.expiration = expiration;
  }

  public String generateToken(UserDetails userDetails) {
    return Jwts
      .builder()
      .subject(userDetails.getUsername())
      .claim("roles", userDetails.getAuthorities())
      .issuedAt(new Date())
      .expiration(new Date(new Date().getTime() + expiration))
      .signWith(getKey(secret))
      .compact();
  }

  public Claims getClaims(String token) {
    validate(token);
    return Jwts.parser().verifyWith(getKey(secret)).build().parseSignedClaims(token).getPayload();
  }

  public void validate(String token) {
    try {
      Jwts.parser().verifyWith(getKey(secret)).build().parseSignedClaims(token).getPayload().getSubject();
    } catch (ExpiredJwtException e) {
      LOG.severe(AuthenticationWebKeys.TOKEN_EXPIRED);
    } catch (MalformedJwtException e) {
      LOG.severe(AuthenticationWebKeys.TOKEN_MALFORMED);
    } catch (SignatureException e) {
      LOG.severe(AuthenticationWebKeys.BAD_SIGNATURE);
    } catch (IllegalArgumentException e) {
      LOG.severe(AuthenticationWebKeys.ILLEGAL_ARGS);
    }
  }

  private SecretKey getKey(String secret) {
    byte[] secretBytes = Decoders.BASE64URL.decode(secret);
    return Keys.hmacShaKeyFor(secretBytes);
  }

}
