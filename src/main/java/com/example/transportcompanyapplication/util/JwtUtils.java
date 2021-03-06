package com.example.transportcompanyapplication.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.token.secret}")
    private String jwtSecret;

    @Value("${jwt.token.lifeTime}")
    private Long tokenLifeTime;

    @Value("${jwt.refresh.lifeTime}")
    private Long refreshLifeTime;

    public String generateJwtToken(UserDetails userDetails){
        return generateJwt(userDetails, tokenLifeTime);
    }

    public String generateRefreshToken(UserDetails userDetails){
        return generateJwt(userDetails, refreshLifeTime);
    }

    private String generateJwt(UserDetails userDetails, Long lifeTime){
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
        Date expireTime = new Date(System.currentTimeMillis() + lifeTime);
        return JWT.create().withSubject(userDetails.getUsername()).withExpiresAt(expireTime)
                .sign(algorithm);
    }

    public boolean validateJwtToken(String jwt){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(jwt);
            return true;
        }catch (JWTVerificationException exception){
            exception.printStackTrace();
            return false;
        }
    }


    public String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }
        return null;
    }

    public String getUserNameFromToken(String jwt) {
        DecodedJWT decodedJwt = JWT.decode(jwt);
        String username = decodedJwt.getSubject();
        return username;
    }
}
