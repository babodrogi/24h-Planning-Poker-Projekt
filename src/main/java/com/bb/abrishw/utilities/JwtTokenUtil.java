package com.bb.abrishw.utilities;

import com.bb.abrishw.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtTokenUtil {

  public static final long JWT_TOKEN_VALIDITY = 60 * 60;

  @Value(value = "${JWT_SECRET}")
  private String secret;

  public int getIdFromToken(String token){
    return Integer.parseInt(getClaimFromToken(token,Claims::getSubject));
  }

  public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  public String generateToken(User user) {
    Map<String,Object> claims = new HashMap<>();
    return doGenerateToken(claims,user.getId());
  }

  private String doGenerateToken(Map<String,Object> claims, Integer UserId) {
    return Jwts.builder().setClaims(claims).setSubject(UserId.toString()).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
        .signWith(SignatureAlgorithm.HS512, secret).compact();
  }
}