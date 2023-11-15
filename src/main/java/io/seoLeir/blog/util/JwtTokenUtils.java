package io.seoLeir.blog.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.*;

@Service
public class JwtTokenUtils {

    @Value("${socialmedia.jwt.lifetime}")
    private Duration lifetime;
    private final Key secretKey;
    private final JwtParser parser;

    public JwtTokenUtils() {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        parser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();
    }

    public String generateToken(String name, Collection<? extends GrantedAuthority> authorities){
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = authorities.stream().map(GrantedAuthority::getAuthority).toList();
        claims.put("roles", roleList);
        Date issDate = new Date(System.currentTimeMillis());
        Date expDate = new Date(issDate.getTime() + lifetime.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(name)
                .setIssuedAt(issDate)
                .setExpiration(expDate)
                .signWith(secretKey).compact();
    }

    public Claims getAllClaimsFromJwtToken(String token){
        return parser
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token){
        return getAllClaimsFromJwtToken(token).getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token){
        return getAllClaimsFromJwtToken(token)
                .get("roles", List.class);
    }
}
