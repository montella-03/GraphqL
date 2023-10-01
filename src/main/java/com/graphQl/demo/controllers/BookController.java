package com.graphQl.demo.controllers;

import com.graphQl.demo.entities.Book;
import com.graphQl.demo.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @QueryMapping
    public Iterable<Book> books() {
        return bookRepository.findAll();
    }
}
