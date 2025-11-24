package com.ecommerce.security;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.Staff;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationMs = 1000 * 60 * 60;

    public String generateTokenForCustomer(Customer c) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "CUSTOMER");
        claims.put("name", c.getName());
        return buildToken(claims, c.getEmail());
    }

    public String generateTokenForStaff(Staff s) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", s.getRole());
        claims.put("name", s.getName());
        return buildToken(claims, s.getEmail());
    }

    private String buildToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key)
                .compact();
    }
}
