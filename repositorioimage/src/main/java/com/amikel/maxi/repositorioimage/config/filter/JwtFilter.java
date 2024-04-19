package com.amikel.maxi.repositorioimage.config.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.amikel.maxi.repositorioimage.application.jwt.JwtInvalidTokenException;
import com.amikel.maxi.repositorioimage.application.jwt.JwtService;
import com.amikel.maxi.repositorioimage.application.users.UserServiceImpl;
import com.amikel.maxi.repositorioimage.domain.entity.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class JwtFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
                
                String token = getToken(request);

                if(token != null){
                    try {
                        String email = jwtService.getEmailFromToken(token);
                        User user = userService.getByEmail(email);
                        setUserAsAuthenticated(user);
                    } catch (JwtInvalidTokenException e) {
                        log.info("Token invalido: ", e.getMessage());
                    }catch(Exception e){
                        log.info("Token error: ", e.getMessage());
                    }
                }
                filterChain.doFilter(request, response);
    }
    
    private void setUserAsAuthenticated(User user){
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null){
            String[] authHeaderParts = authHeader.split(" ");
            if(authHeaderParts.length == 2){
                return authHeaderParts[1];
            }
        }
        return null;
    }
}
