package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOKS")
@PrimaryKeyJoinColumn(name = "PRODUCTID")
public class Books extends Product {

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PUBLISHER")
    private String publisher;
}
