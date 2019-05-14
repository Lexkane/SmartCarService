package ua.ita.smartcarservice.security;

import io.jsonwebtoken.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.service.UserPrinciple;

import java.lang.invoke.MethodHandles;
import java.util.Date;

/**
 * This class implements useful functions:
 * 1. Generate a JWT token
 * 2. Validate a JWT token
 * 3. Parse username from JWT token
 */

@Component
public class TokenProvider {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    @Value("${jwt.tokenSecret}")
    private String secret;

    @Value("${jwt.accessTokenExpiration}")
    private int expirationTime;

    public String generateToken(Authentication authentication) {

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrinciple.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: " + e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: " + e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token: " + e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token: " + e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: " + e);
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

}
