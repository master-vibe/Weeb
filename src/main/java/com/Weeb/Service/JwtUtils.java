package com.Weeb.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtils{
    private int EXPIRATION = 1000*60*60;

    @Value("${secuirty.jwt.secret-key}")
    private String SECRET_KEY;
    
    public String generateToken(UserDetails userDetails){
        return buildToken(new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String,Object> extraClaim,UserDetails userDetails){
        return buildToken(extraClaim,userDetails);
    }

    private String buildToken(Map<String,Object> extraClaim, UserDetails userDetails) {
        return Jwts
            .builder()
            .subject(userDetails.getUsername())
            .claims(extraClaim)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+EXPIRATION))
            .signWith(getSignInKey(),Jwts.SIG.HS256)
            .compact();
    }

    public boolean isTokenValid(String token){
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return ((Claims)Jwts
                        .parser()
                        .verifyWith(getSignInKey())
                        .build()
                        .parse(token)
                        .getPayload());
    }

    public String extractUsername(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (Exception e) {
            return null;
        }
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }
}
