package com.merobazar.ecommerce.security;

import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.merobazar.ecommerce.entity.User;
import com.merobazar.ecommerce.service.UserService;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //dependenices
    private final JwtUtil jwtUtil;
    private final UserService userService;

    //constructor injection
    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil; //dependency injection
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                      FilterChain filterChain)throws ServletException, IOException {
                                        
        String authHeader = request.getHeader("Authorization");//read the authorization header from the request

        if (authHeader != null && authHeader.startsWith("Bearer")) { //check if header starts with bearer
            String token = authHeader.substring(7);//removes the bearer part to get the token only

            if (jwtUtil.validateToken(token)) { //validate the token 
                UUID userId = jwtUtil.getUserId(token); //extracts the userId from token
                User user = userService.getUser(userId);

                //if user exists then create a spring security authentication object
                if (user != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));

                         //   sets the authentication in spring secruity context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        //this is important!! it continues the filter chain 
        //if we missed it  then the request will stop here and never reach to the controller
        filterChain.doFilter(request, response);

    }

}