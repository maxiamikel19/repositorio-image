package com.amikel.maxi.repositorioimage.application.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.amikel.maxi.repositorioimage.domain.AccessToken;
import com.amikel.maxi.repositorioimage.domain.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
    
    private final SecretKeyGenerator keyGenerator;

    public AccessToken generateToken(User user){

        SecretKey key = keyGenerator.getKey();
        Date expirationDate = generateExpirationDate();
        Map<String, Object> claims = generateTokenUserClaims(user);
        
        String token = Jwts
                .builder()
                .signWith(key)
                .subject(user.getEmail())
                .expiration(expirationDate)
                .claims(claims)
                .compact();
        return new AccessToken(token);
    }

    private Date generateExpirationDate(){
        var expirationMinutes = 60;
        LocalDateTime now = LocalDateTime.now().plusMinutes(expirationMinutes);
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Map<String, Object> generateTokenUserClaims(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getName());
        claims.put("created", user.getCreatedAt().getDayOfMonth() + "/" + user.getCreatedAt().getMonthValue() + "/" + user.getCreatedAt().getYear());
        return claims;
    }

    public String getEmailFromToken(String tokenJwt){
        try {
            JwtParser build = Jwts.parser().verifyWith(keyGenerator.getKey()).build();

            Jws<Claims> claimsJts = build.parseSignedClaims(tokenJwt);

            Claims claims = claimsJts.getPayload();
            
            return claims.getSubject();

        } catch (JwtException e) {
            throw new JwtInvalidTokenException(e.getMessage());
        }
       
    }
    
}
