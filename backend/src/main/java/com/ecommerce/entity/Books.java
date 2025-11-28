package com.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
public class Books extends Product {

    private String author;
    private String isbn;

    public Books() {}

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
