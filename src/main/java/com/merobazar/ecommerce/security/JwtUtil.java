package com.merobazar.ecommerce.security;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.merobazar.ecommerce.entity.User;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY;
    private final long jwtExpirationMs;

    //constructor loads env variables
    public JwtUtil() {
        Dotenv dotenv = Dotenv.load();
        this.SECRET_KEY = dotenv.get("JWT_SECRET");
        this.jwtExpirationMs = Long.parseLong(dotenv.get("JWT_EXPIRATION_MS"));
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString()) //set userID as subject
                .claim("role", user.getRole().name()) //gets user role in claims
                .setIssuedAt(new Date()) //sets token issued date
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                //sign token using HMAC SHA-256 algorithm and secret key
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public UUID getUserId(String token) {

        Claims claims = Jwts.parserBuilder()
        //parms the token and get claims
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token) //parse the jwt
                .getBody();

                //return the subject (user id ) as uuid
        return UUID.fromString(claims.getSubject());

    }

    public boolean validateToken(String token) {
        try {
            //parse the token with secret eky
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            return true;  //token is valid
        } catch (JwtException e) {
            return false; //token expired or invalid
        }
    }
}
