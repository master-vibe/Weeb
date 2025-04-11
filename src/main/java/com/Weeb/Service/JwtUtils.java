package com.Weeb.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

    private boolean ValidateToken(String token){
        return true;
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }
}
