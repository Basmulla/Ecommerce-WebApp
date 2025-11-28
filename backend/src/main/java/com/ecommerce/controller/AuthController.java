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
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final CustomerService customerService;
    private final StaffService staffService;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // ============================================================
    // LOGIN (Frontend expects ONLY this)
    // ============================================================
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {

        // Try staff first
        Staff staff = staffService.getByEmail(req.getEmail());
        if (staff != null && encoder.matches(req.getPassword(), staff.getPassword())) {

            String token = jwtService.generateToken(
                    staff.getEmail(),
                    staff.getRole(),
                    staff.getStaffId()
            );

            return new LoginResponse(
                    token,
                    new UserResponse(
                            staff.getStaffId(),
                            staff.getName(),
                            staff.getEmail(),
                            staff.getRole()
                    )
            );
        }

        // Try customer
        Customer customer = customerService.getByEmail(req.getEmail());
        if (customer != null && encoder.matches(req.getPassword(), customer.getPassword())) {

            String token = jwtService.generateToken(
                    customer.getEmail(),
                    "CUSTOMER",
                    customer.getCustomerId()
            );

            return new LoginResponse(
                    token,
                    new UserResponse(
                            customer.getCustomerId(),
                            customer.getName(),
                            customer.getEmail(),
                            "CUSTOMER"
                    )
            );
        }

        throw new RuntimeException("Invalid email or password");
    }

    // ============================================================
    // REGISTER
    // Used by frontend or optional
    // ============================================================
    @PostMapping("/register")
    public LoginResponse register(@RequestBody RegisterRequest req) {

        // Create new customer
        Customer customer = new Customer();
        customer.setCustomerId(null); // DB sequence
        customer.setName(req.getName());
        customer.setEmail(req.getEmail());
        customer.setPassword(encoder.encode(req.getPassword()));
        Customer saved = customerService.create(customer);

        // Generate token
        String token = jwtService.generateToken(saved.getEmail(), "CUSTOMER", saved.getCustomerId());

        return new LoginResponse(
                token,
                new UserResponse(
                        saved.getCustomerId(),
                        saved.getName(),
                        saved.getEmail(),
                        "CUSTOMER"
                )
        );
    }


    // ============================================================
    // DTOs
    // ============================================================

    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    public static class RegisterRequest {
        private String name;
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static class LoginResponse {
        private String token;
        private UserResponse user;
    }

    @Data
    @AllArgsConstructor
    public static class UserResponse {
        private Long id;
        private String name;
        private String email;
        private String role;
    }
}
