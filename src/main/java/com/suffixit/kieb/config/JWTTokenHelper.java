package com.suffixit.kieb.config;


import com.suffixit.kieb.entities.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.security.Key;
import java.util.*;
import java.util.function.Function;


@Component
public class JWTTokenHelper {


    @Value("${jwt.auth.secret_key}")
    private String secretKey;

    @Value("${jwt.auth.expires_in}")
    private int expiresIn;



    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public String generateToken(Users userDetails) {
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .toList();

        if (!roles.get(0).equals("ADMIN")) {
            return generateToken(new HashMap<>(), userDetails, userDetails.getMember().getId());
        }

        return generateToken(new HashMap<>(), userDetails, null);
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);

        if(isTokenExpired(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED ,"Token is expired. Please login again !");
        }
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, Integer memberId) {

        extraClaims.put("roles",userDetails.getAuthorities());
        extraClaims.put("memberId" , memberId);

        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    public String getToken( HttpServletRequest request ) {
        String bearerPrefix = "Bearer ";
        String authHeader = request.getHeader("Authorization");
        if ( authHeader != null && authHeader.toLowerCase().startsWith(bearerPrefix.toLowerCase())) {
            return authHeader.substring(7);
        }

        return null;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
