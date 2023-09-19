package io.seoLeir.socialmedia.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.seoLeir.socialmedia.config.JwtConfigurationProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {

    private final JwtConfigurationProperties jwtConfigurationProperties;
    private Key secretKey;
    private JwtParser parser;

    @PostConstruct
    private void init(){
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public String generateToken(String name, Collection<? extends GrantedAuthority> authorities){
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = authorities.stream().map(GrantedAuthority::getAuthority).toList();
        claims.put("roles", roleList);
        Date issDate = new Date();
        Date expDate = new Date(issDate.getTime() + jwtConfigurationProperties.getLifetime().toMillis());
        return Jwts.builder().setClaims(claims)
                .setSubject(name)
                .setIssuedAt(issDate)
                .setExpiration(expDate)
                .signWith(secretKey).compact();
    }

    public Claims getAllClaimsFromJwtToken(String token){
        return parser.parseClaimsJws(token).getBody();
    }

    public String getUsername(String token){
        return getAllClaimsFromJwtToken(token).getSubject();
    }

    public List<String> getRoles(String token){
        return getAllClaimsFromJwtToken(token)
                .get("roles", List.class);
    }

}
