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

    public List<Books> getAll() {
        return repo.findAll();
    }

    public Books getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Books create(Books book) {
        return repo.save(book);
    }

    public Books update(Long id, Books updated) {
        if (!repo.existsById(id)) return null;
        updated.setProductId(id);
        return repo.save(updated);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    public Books findByISBN(String isbn) {
        return repo.findByISBN(isbn);
    }

    public List<Books> findByAuthor(String author) {
        return repo.findByAuthor(author);
    }
}
