package com.portalst.cadastroempresa.middlewares;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secret; 

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        String token = extractToken(request);

        if (token != null) {
            try {
                Claims claims;
                try {
                    JwtParser parser = Jwts.parser();
                    parser = parser.setSigningKey(secret);
                    Jws<Claims> jws = parser.parseClaimsJws(token);
                    claims = jws.getBody();
                } catch (Exception e) {
                    e.printStackTrace();
                    claims = null;
                }

                if (claims != null) {

                    String username = claims.getSubject();
                    String userType = claims.get("userType", String.class);


                    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + userType));

                    UserDetails userDetails = new User(username, "", authorities);

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado");
                return;
            }
        } else {
            System.out.println(">>> Nenhum token encontrado no request");
        }

        filterChain.doFilter(request, response);
        System.out.println(">>> Filtro JWT concluído");
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            JwtParser parser = Jwts.parser();
            parser = parser.setSigningKey(secret.getBytes()); 
            Jws<Claims> jws = parser.parseClaimsJws(token);
            claims = jws.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            claims = null;
        }
        return claims;
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}