package com.pknu26.book_mng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pknu26.book_mng.entity.Book;
import com.pknu26.book_mng.service.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("/book")

public class BookController {

    private final BookService bookService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book_list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        return "book_create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/book/list";
    }

    @GetMapping("/{id}")
    public String datail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "book_detail";
    }
    
}
