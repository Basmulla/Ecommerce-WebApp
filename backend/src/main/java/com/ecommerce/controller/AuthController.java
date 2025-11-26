package com.ecommerce.controller;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.Staff;
import com.ecommerce.security.JwtService;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.StaffService;
import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.AuthResponse;
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
    // STAFF LOGIN
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

    // -------------------------------------------------------------
    // CUSTOMER LOGIN
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
}
