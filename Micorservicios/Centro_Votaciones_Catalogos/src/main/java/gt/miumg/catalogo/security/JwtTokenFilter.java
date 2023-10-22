/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.catalogo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private String jwtSecret = "GEavVBSE14llI/V6yusCYxbQXJzaI/kHjqGQC4k8TJlRxIZwAj7MCWR3wV8cfkd4q6PQmgkApfJJFid8YdLBzg==";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("Se accedio a los headers");
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7); // Excluye "Bearer " del token
            System.out.println("tokend enviado: " + jwt);
            filterChain.doFilter(request, response);
            return;
            /*
            try {

                
                Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

                Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt);
                 
            } catch (Exception e) {
                System.out.println("AQUI DA EL 401");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
             */
        }
        //response.setStatus(HttpStatus.UNAUTHORIZED.value());
        //filterChain.doFilter(request, response);
    }
}
