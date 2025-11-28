package com.ecommerce.controller;

import com.ecommerce.entity.Manager;
import com.ecommerce.entity.Processor;
import com.ecommerce.entity.Shipper;
import com.ecommerce.entity.Staff;
import com.ecommerce.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class StaffController {

    private final StaffService staffService;

    // ============================================================
    // CREATE STAFF (GENERIC)
    // ============================================================
    @PostMapping("/create")
    public Staff create(@RequestBody Staff staff) {
        return staffService.create(staff);
    }

    // ============================================================
    // CREATE MANAGER
    // ============================================================
    @PostMapping("/manager/create")
    public Manager createManager(@RequestBody Manager manager) {
        return staffService.createManager(manager);
    }

    // ============================================================
    // CREATE PROCESSOR
    // ============================================================
    @PostMapping("/processor/create")
    public Processor createProcessor(@RequestBody Processor processor) {
        return staffService.createProcessor(processor);
    }

    // ============================================================
    // CREATE SHIPPER
    // ============================================================
    @PostMapping("/shipper/create")
    public Shipper createShipper(@RequestBody Shipper shipper) {
        return staffService.createShipper(shipper);
    }

    // ============================================================
    // GET ALL STAFF
    // ============================================================
    @GetMapping("/all")
    public List<Staff> getAll() {
        return staffService.getAll();
    }

    // ============================================================
    // GET STAFF BY ID
    // ============================================================
    @GetMapping("/{id}")
    public Staff getById(@PathVariable Long id) {
        return staffService.getById(id);
    }

    // ============================================================
    // GET STAFF BY ROLE
    // Example: /api/staff/role/MANAGER
    // ============================================================
    @GetMapping("/role/{role}")
    public List<Staff> getByRole(@PathVariable String role) {
        return staffService.getByRole(role);
    }

    // ============================================================
    // UPDATE STAFF
    // ============================================================
    @PutMapping("/update/{id}")
    public Staff update(
            @PathVariable Long id,
            @RequestBody Staff updatedStaff
    ) {
        return staffService.update(id, updatedStaff);
    }

    // ============================================================
    // DELETE STAFF
    // ============================================================
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return staffService.delete(id);
    }
}
