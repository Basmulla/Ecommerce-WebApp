package com.ecommerce.controller;

import com.ecommerce.dto.AuthResponse;
import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.entity.Customer;
import com.ecommerce.entity.Staff;
import com.ecommerce.security.JwtService;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final StaffService staffService;
    private final CustomerService customerService;
    private final JwtService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // ============================================================
    // LOGIN (used by frontend)
    // ============================================================
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {

        // STAFF LOGIN
        Staff staff = staffService.getByEmail(req.getEmail());
        if (staff != null && encoder.matches(req.getPassword(), staff.getPassword())) {

            String token = jwtService.generateToken(
                    staff.getEmail(),
                    staff.getRole(),
                    staff.getStaffId()
            );

            return new AuthResponse(
                    token,
                    staff.getRole(),
                    staff.getStaffId(),
                    staff.getName(),
                    staff.getEmail()
            );
        }

        // CUSTOMER LOGIN
        Customer customer = customerService.getByEmail(req.getEmail());
        if (customer != null && encoder.matches(req.getPassword(), customer.getPassword())) {

            String token = jwtService.generateToken(
                    customer.getEmail(),
                    "CUSTOMER",
                    customer.getCustomerId()
            );

            return new AuthResponse(
                    token,
                    "CUSTOMER",
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getEmail()
            );
        }

        return new AuthResponse(null, "INVALID", null, null, null);
    }

    // ============================================================
    // REGISTER (frontend uses this)
    // ============================================================
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest req) {

        Customer c = new Customer();
        c.setName(req.getName());
        c.setEmail(req.getEmail());
        c.setPassword(encoder.encode(req.getPassword()));

        Customer saved = customerService.create(c);

        String token = jwtService.generateToken(saved.getEmail(), "CUSTOMER", saved.getCustomerId());

        return new AuthResponse(
                token,
                "CUSTOMER",
                saved.getCustomerId(),
                saved.getName(),
                saved.getEmail()
        );
    }
}
