package com.example.securityzerotoend.security.jwt;

import com.example.securityzerotoend.model.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService {
    /* @Value("${jwt.token.validity}")
     private Long JWT_TOKEN_VALIDITY;*/
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    //    private static final String JWT_TOKEN_SECRET_KEY = "532d3d6f5b31577e4f70786b7e594c33506b30566b505445664f6161534e2f32";
    /*@Value("${jwt.token.secret}")
    private String JWT_TOKEN_SECRET_KEY;*/
    //WEP Key Generator
    private static final String JWT_TOKEN_SECRET_KEY = "227a34642a72625769433b5f2573616d2f2937773e203131547c7769335c4b40";


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_TOKEN_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserEntity userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(
            Map<String, Object> extractClaims,
            UserEntity userDetails
    ) {
//        String id = UUID.randomUUID().toString().replace("-", "");
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
//                .setId(id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("userName", userDetails.getUsername())
//                .claim("password", userDetails.getPassword())
                .claim("role", userDetails.getRole())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = extractExpirationDate(token);
        return expiration.before(new Date());
    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /*public String extractId(String token) {
        return extractClaim(token, Claims::getId);
    }*/
}
