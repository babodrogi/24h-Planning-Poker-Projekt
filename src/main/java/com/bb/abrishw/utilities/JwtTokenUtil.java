//package com.bb.abrishw.utilities;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//
//@Component
//public class JwtTokenUtil {
//
//  public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;
//
//  @Value(value = "${JWT_SECRET}")
//  private String secret;
//
//
//  public String getTokenFromRequest(HttpServletRequest request){
//    return request.getHeader("jwt").substring(7);
//  }
//
//  public String getUsernameFromToken(String token) {
//    return getAllClaimsFromToken(token).get("username").toString();
//  }
//
//  public Date getExpirationDateFromToken(String token) {
//    return getClaimFromToken(token, Claims::getExpiration);
//  }
//
//  public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
//    final Claims claims = getAllClaimsFromToken(token);
//    return claimsResolver.apply(claims);
//  }
//
//  private Claims getAllClaimsFromToken(String token) {
//    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//  }
//
//  public Boolean isTokenExpired(String token) {
//    final Date expiration = getExpirationDateFromToken(token);
//    return expiration.before(new Date());
//  }
//
//  public String generateToken(User user) {
//    Map<String,Object> claims = new HashMap<>();
//    claims.put("username",user.getUsername());
//    return doGenerateToken(claims,user.getId());
//  }
//
//  private String doGenerateToken(Map<String,Object> claims, Long UserId) {
//    return Jwts.builder().setClaims(claims).setSubject(UserId.toString()).setIssuedAt(new Date(System.currentTimeMillis()))
//        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//        .signWith(SignatureAlgorithm.HS512, secret).compact();
//  }
//
////  public Boolean validateToken(String token, UserDetails userDetails) {
////    final String username = getUsernameFromToken(token);
////    return (username.equals(userDetails.getUsername()) &amp;&amp; !isTokenExpired(token));
////  }
//}