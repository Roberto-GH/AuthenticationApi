package co.com.pragma.api.jwt;

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

  @Value("${jwt.secret}")
  private String secret;
  @Value("${jwt.expiration}")
  private Integer expiration;

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
      LOG.severe("token expired");
    } catch (UnsupportedJwtException e) {
      LOG.severe("token unsupported");
    } catch (MalformedJwtException e) {
      LOG.severe("token malformed");
    } catch (SignatureException e) {
      LOG.severe("bad signature");
    } catch (IllegalArgumentException e) {
      LOG.severe("illegal args");
    }
  }

  private SecretKey getKey(String secret) {
    byte[] secretBytes = Decoders.BASE64URL.decode(secret);
    return Keys.hmacShaKeyFor(secretBytes);
  }

}
