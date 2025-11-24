package com.ecommerce.security;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.Staff;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(
            "ThisIsASecretKeyForJwtGeneration12345".getBytes()
    );

    public String generateTokenForCustomer(Customer c) {
        return Jwts.builder()
                .setSubject("customer")
                .claim("customerId", c.getCustomerId())
                .claim("name", c.getName())
                .claim("email", c.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(secretKey)
                .compact();
    }

    public String generateTokenForStaff(Staff s) {
        return Jwts.builder()
                .setSubject("staff")
                .claim("staffId", s.getStaffId())
                .claim("name", s.getName())
                .claim("email", s.getEmail())
                .claim("role", s.getRole()) // Manager / Processor / Shipper
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(secretKey)
                .compact();
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }
}
