package com.ecommerce.repository;

import com.ecommerce.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Long> {

    List<Books> findByAuthor(String author);

    Books findByIsbn(String isbn);
}
