package com.pknu26.book_mng.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pknu26.book_mng.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
