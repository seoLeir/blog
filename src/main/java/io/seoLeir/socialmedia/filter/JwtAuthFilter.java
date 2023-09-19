package io.seoLeir.socialmedia.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.seoLeir.socialmedia.util.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authentication");
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            try {
                String jwt = authHeader.substring(7);
                String username = jwtTokenUtils.getUsername(jwt);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() != null){
                    SecurityContextHolder.getContext()
                            .setAuthentication(new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new)
                                            .toList()));
                }
            } catch (ExpiredJwtException e){
                log.debug("token has expired ", e);

            } catch (SignatureException e){
                // TODO доработка выброса конкретного исключения
                log.debug("incorrect signature for token", e);
            }
        }
        filterChain.doFilter(request, response);
    }
}
