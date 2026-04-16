package com.pknu26.book_mng.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor                                                                                                                                                                                                                                                                                                                                                                                                                                               
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "SEQ_BOOK", allocationSize = 1)
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "TITLE", nullable = false, length = 300)
    private String title;

    @Column(name = "AUTHOR", length = 200)
    private String author;

    @Column(name = "PUBLISHER", length = 200)
    private String publisher;

    @Column(name = "ISBN", unique = true, length = 20)
    private String isbn;

    @Column(name = "CATEGORY", length = 100)
    private String category;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "PUBLISHED_DATE")
    private LocalDate publishedDate;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.stock == null) {
            this.stock = 0;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
