package com.pknu26.book_mng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pknu26.book_mng.entity.Book;
import com.pknu26.book_mng.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // 도서 등록(Create)
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // 전체 조회(Read)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // 단건 조회(Read)
    public Book findById(Long id) {
        return bookRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 도서는 없습니다."));
    }

    // 수정(Update)
    // public Book update(Long id, Book updateBook) {
    //     Book book = findById(id);

    //     book.setTitle(update);
    // }
}
