package com.hugoms154.psiproj.configuration

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.time.Duration
import java.util.*

@Service
class JwtService {
  val SECRET: String = "Y928N4VTO8AW8YTNVA7W4YRKX9C3W8YNTB7E4YT7IEC"

  fun generateToken(userDetails: UserDetails) = generateToken(HashMap(), userDetails)

  fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
    return Jwts
      .builder()
      .setClaims(extraClaims)
      .setSubject(userDetails.username)
      .setIssuedAt(Date(System.currentTimeMillis()))
      .setExpiration(Date(System.currentTimeMillis() + Duration.ofDays(1).toMillis()))
      .signWith(getSigningKey(), SignatureAlgorithm.HS256)
      .compact()
  }

  fun isValidToken(token: String, userDetails: UserDetails): Boolean {
    val username = extractUsername(token)
    return (username == userDetails.username) && !isTokenExpired(token)
  }

  fun isTokenExpired(token: String): Boolean {
    return extractExpiration(token).before(Date())
  }

  private fun extractExpiration(token: String): Date {
    return this.extractClaim(token, Claims::getExpiration)
  }

  fun extractUsername(token: String): String {
    return extractClaim(token, Claims::getSubject)
  }

  fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
    val claims = extractAllClaims(token)
    return claimsResolver(claims)
  }

  fun extractAllClaims(token: String): Claims {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSigningKey())
      .build()
      .parseClaimsJws(token)
      .body
  }

  private fun getSigningKey(): Key {
    val keyBytes: ByteArray = Decoders.BASE64.decode(this.SECRET)
    return Keys.hmacShaKeyFor(keyBytes)
  }
}
