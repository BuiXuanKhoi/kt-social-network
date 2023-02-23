package com.example.socialnetworkproject.security;

import com.example.socialnetworkproject.models.entities.CustomUserDetails;
import io.jsonwebtoken.*;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {

    private final String JWT_SECRET = "kt-social-network";

    private final long JWT_EXPIRATION = 604800000L;
    public String generateToken(CustomUserDetails userDetails){
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime expiryDateTime = now.plus(JWT_EXPIRATION, ChronoUnit.MILLIS);

        return Jwts.builder()
                .setSubject(userDetails.getUser().getUserId().toString())
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiryDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public UUID getUserIdFromJwt(String token){
        Claims claims = Jwts.parser()
                            .setSigningKey(JWT_SECRET)
                            .parseClaimsJws(token)
                            .getBody();
        return UUID.fromString(claims.getSubject());
    }

    public boolean validateToken(String authToken){
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        }catch (MalformedJwtException ex) {
            throw new IllegalArgumentException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new IllegalArgumentException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new IllegalArgumentException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("JWT claims string is empty.");
        }
    }
}
