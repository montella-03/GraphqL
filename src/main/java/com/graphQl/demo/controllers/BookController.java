package com.graphQl.demo.controllers;

import com.graphQl.demo.entities.Author;
import com.graphQl.demo.entities.Book;
import com.graphQl.demo.model.BookRequest;
import com.graphQl.demo.repository.AuthorRepository;
import com.graphQl.demo.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public Iterable<Book> books() {
        return bookRepository.findAll();
    }
    @MutationMapping
    public Book createBook(@Argument String bookName,@Argument String isbn, @Argument Long authorId){
        Author author = authorRepository.findById(authorId).orElseThrow(
                ()-> new RuntimeException("Author not found")
        );
        Book book = Book.builder()
                .bookName(bookName)
                .isbn(isbn)
                .author(author)
                .build();
        return bookRepository.save(book);
    }
    @MutationMapping
    public Book addBookToAuthor(@Argument(name = "book") BookRequest bookRequest){
        Author author = authorRepository.findById(bookRequest.authorId()).orElseThrow(
                ()-> new RuntimeException("Author not found")
        );
        Book book = Book.builder()
                .bookName(bookRequest.bookName())
                .isbn(bookRequest.isbn())
                .author(author)
                .build();
        return bookRepository.save(book);
    }
    @MutationMapping
    public Book updateBook(@Argument(name = "book") BookRequest bookRequest, @Argument Long id){
        Book book = bookRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Book not found")
        );
        if(bookRequest.bookName()!=null){
            book.setBookName(bookRequest.bookName());
        }
        if(bookRequest.isbn()!=null){
            book.setIsbn(bookRequest.isbn());
        }
        return bookRepository.save(book);
    }
    @MutationMapping
    public Book deleteBook(@Argument Long id){
        Book book = bookRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Book not found")
        );
        bookRepository.delete(book);
        return book;

    }
}
