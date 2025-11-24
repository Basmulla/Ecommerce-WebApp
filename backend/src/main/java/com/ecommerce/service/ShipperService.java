package com.ecommerce.service;

import com.ecommerce.entity.Shipper;
import com.ecommerce.repository.ShipperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipperService {

    private final ShipperRepository repo;

    public List<Shipper> getAll() {
        return repo.findAll();
    }

    public Shipper getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Shipper create(Shipper s) {
        return repo.save(s);
    }

    public Shipper update(Long id, Shipper updated) {
        if (!repo.existsById(id)) return null;
        updated.setStaffId(id);
        return repo.save(updated);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
