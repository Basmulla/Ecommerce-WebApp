package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Books")
@PrimaryKeyJoinColumn(name = "ProductID")
public class Books extends Product {

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "Author")
    private String author;

    @Column(name = "Publisher")
    private String publisher;
}
