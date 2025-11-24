package com.ecommerce.controllers;

import com.ecommerce.entity.*;
import com.ecommerce.service.ManagerService;
import com.ecommerce.service.ProcessorService;
import com.ecommerce.service.ShipperService;
import com.ecommerce.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin("*")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ProcessorService processorService;

    @Autowired
    private ShipperService shipperService;

    // ===============================================================
    // STAFF (SUPERCLASS)
    // ===============================================================

    @PostMapping("/create")
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        Staff saved = staffService.createStaff(staff);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        Staff s = staffService.getStaffById(id);
        if (s == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(s);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Staff> updateStaff(
            @PathVariable Long id,
            @RequestBody Staff newData) {

        Staff updated = staffService.updateStaff(id, newData);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long id) {
        boolean ok = staffService.deleteStaff(id);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Staff deleted successfully.");
    }

    // ===============================================================
    // FILTER BY ROLE
    // ===============================================================

    @GetMapping("/role/{role}")
    public ResponseEntity<List<Staff>> getStaffByRole(@PathVariable String role) {
        return ResponseEntity.ok(staffService.getStaffByRole(role));
    }

    // ===============================================================
    // MANAGER
    // ===============================================================

    @PostMapping("/manager/create")
    public ResponseEntity<Manager> createManager(@RequestBody Manager manager) {
        return ResponseEntity.ok(managerService.createManager(manager));
    }

    @GetMapping("/manager/all")
    public ResponseEntity<List<Manager>> getAllManagers() {
        return ResponseEntity.ok(managerService.getAllManagers());
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<Manager> getManager(@PathVariable Long id) {
        Manager m = managerService.getManager(id);
        if (m == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(m);
    }

    // ===============================================================
    // PROCESSOR
    // ===============================================================

    @PostMapping("/processor/create")
    public ResponseEntity<Processor> createProcessor(@RequestBody Processor processor) {
        return ResponseEntity.ok(processorService.createProcessor(processor));
    }

    @GetMapping("/processor/all")
    public ResponseEntity<List<Processor>> getAllProcessors() {
        return ResponseEntity.ok(processorService.getAllProcessors());
    }

    @GetMapping("/processor/{id}")
    public ResponseEntity<Processor> getProcessor(@PathVariable Long id) {
        Processor p = processorService.getProcessor(id);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    // ===============================================================
    // SHIPPER
    // ===============================================================

    @PostMapping("/shipper/create")
    public ResponseEntity<Shipper> createShipper(@RequestBody Shipper shipper) {
        return ResponseEntity.ok(shipperService.createShipper(shipper));
    }

    @GetMapping("/shipper/all")
    public ResponseEntity<List<Shipper>> getAllShippers() {
        return ResponseEntity.ok(shipperService.getAllShippers());
    }

    @GetMapping("/shipper/{id}")
    public ResponseEntity<Shipper> getShipper(@PathVariable Long id) {
        Shipper s = shipperService.getShipper(id);
        if (s == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(s);
    }
}
