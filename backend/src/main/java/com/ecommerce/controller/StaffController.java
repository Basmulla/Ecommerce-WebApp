package com.ecommerce.controller;

import com.ecommerce.entity.Staff;
import com.ecommerce.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public List<Staff> getAll() {
        return staffService.getAll();
    }

    @GetMapping("/{id}")
    public Staff getById(@PathVariable Long id) {
        return staffService.getById(id);
    }

    @PostMapping
    public Staff create(@RequestBody Staff s) {
        return staffService.create(s);
    }

    @PutMapping("/{id}")
    public Staff update(@PathVariable Long id, @RequestBody Staff s) {
        return staffService.update(id, s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        staffService.delete(id);
    }
}
