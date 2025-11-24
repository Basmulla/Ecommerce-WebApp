package com.ecommerce.controller;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.Staff;
import com.ecommerce.security.JwtService;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.StaffService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final CustomerService customerService;
    private final StaffService staffService;
    private final JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // -------------------------------------------------------------
    // 1. Staff Login
    // -------------------------------------------------------------
    @PostMapping("/login/staff")
    public AuthResponse staffLogin(@RequestBody LoginRequest request) {

        Staff staff = staffService.findByEmail(request.getEmail());
        if (staff == null) {
            return new AuthResponse(null, "Invalid email or password", null, null, null);
        }

        if (!passwordEncoder.matches(request.getPassword(), staff.getPassword())) {
            return new AuthResponse(null, "Invalid email or password", null, null, null);
        }

        // JWT Generation
        String token = jwtService.generateToken(staff.getEmail(), staff.getRole(), staff.getStaffId());

        return new AuthResponse(
                token,
                "STAFF_LOGIN_SUCCESS",
                staff.getRole(),
                staff.getName(),
                staff.getEmail()
        );
    }

    // -------------------------------------------------------------
    // 2. Customer Login
    // -------------------------------------------------------------
    @PostMapping("/login/customer")
    public AuthResponse customerLogin(@RequestBody LoginRequest request) {

        Customer customer = customerService.findByEmail(request.getEmail());
        if (customer == null) {
            return new AuthResponse(null, "Invalid email or password", null, null, null);
        }

        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            return new AuthResponse(null, "Invalid email or password", null, null, null);
        }

        // Customer has no role in DB → assign manually
        String token = jwtService.generateToken(customer.getEmail(), "CUSTOMER", customer.getCustomerId());

        return new AuthResponse(
                token,
                "CUSTOMER_LOGIN_SUCCESS",
                "CUSTOMER",
                customer.getName(),
                customer.getEmail()
        );
    }

    // -------------------------------------------------------------
    // Request + Response DTOs
    // -------------------------------------------------------------
    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static class AuthResponse {
        private String token;
        private String message;
        private String role;
        private String name;
        private String email;
    }
}
