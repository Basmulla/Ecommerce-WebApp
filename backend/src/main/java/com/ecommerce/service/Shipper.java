package com.ecommerce.service;

import com.ecommerce.entity.Processor;
import com.ecommerce.repository.ProcessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessorService {

    private final ProcessorRepository repo;

    public List<Processor> getAll() {
        return repo.findAll();
    }

    public Processor getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Processor create(Processor processor) {
        return repo.save(processor);
    }

    public Processor update(Long id, Processor updated) {
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
