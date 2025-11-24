package com.ecommerce.controller;

import com.ecommerce.entity.Staff;
import com.ecommerce.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService service;

    @GetMapping
    public List<Staff> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Staff getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Staff create(@RequestBody Staff staff) {
        return service.create(staff);
    }

    @PutMapping("/{id}")
    public Staff update(@PathVariable Long id, @RequestBody Staff updated) {
        return service.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
