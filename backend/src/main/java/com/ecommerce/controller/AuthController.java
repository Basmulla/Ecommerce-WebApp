package com.ecommerce.controller;

import com.ecommerce.dto.AuthResponse;
import com.ecommerce.dto.LoginRequest;
import com.ecommerce.entity.Customer;
import com.ecommerce.entity.Staff;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.repository.StaffRepository;
import com.ecommerce.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer c) {
        if (customerRepository.findByEmail(c.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use.");
        }

        c.setPassword(passwordEncoder.encode(c.getPassword()));
        Customer saved = customerRepository.save(c);

        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        Staff staff = staffRepository.findByEmail(req.getEmail()).orElse(null);
        if (staff != null) {
            if (!passwordEncoder.matches(req.getPassword(), staff.getPassword())) {
                return ResponseEntity.status(401).body("Invalid credentials");
            }

            String token = jwtService.generateTokenForStaff(staff);

            AuthResponse resp = new AuthResponse(
                    token,
                    staff.getRole(),
                    staff.getStaffId(),
                    staff.getName(),
                    staff.getEmail()
            );

            return ResponseEntity.ok(resp);
        }

        Customer cust = customerRepository.findByEmail(req.getEmail()).orElse(null);
        if (cust == null || !passwordEncoder.matches(req.getPassword(), cust.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtService.generateTokenForCustomer(cust);

        AuthResponse resp = new AuthResponse(
                token,
                "CUSTOMER",
                cust.getCustomerId(),
                cust.getName(),
                cust.getEmail()
        );

        return ResponseEntity.ok(resp);
    }
}
