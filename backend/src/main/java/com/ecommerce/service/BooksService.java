package com.ecommerce.service;

import com.ecommerce.entity.Books;
import com.ecommerce.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksService {

    private final BooksRepository repo;

    public Books create(Books b) {
        return repo.save(b);
    }

    public Books getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Books> getAll() {
        return repo.findAll();
    }

    public List<Books> getByIsbn(String isbn) {
        return repo.findByIsbn(isbn);
    }

    public List<Books> getByAuthor(String author) {
        return repo.findByAuthorContainingIgnoreCase(author);
    }

    public Books update(Long id, Books updated) {
        Books b = getById(id);
        if (b == null) return null;

        b.setName(updated.getName());
        b.setPrice(updated.getPrice());
        b.setBrand(updated.getBrand());
        b.setActive(updated.isActive());
        b.setAuthor(updated.getAuthor());
        b.setIsbn(updated.getIsbn());

        return repo.save(b);
    }

    public boolean delete(Long id) {
        repo.deleteById(id);
        return true;
    }
}
