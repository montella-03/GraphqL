package com.graphQl.demo.config;

import com.graphQl.demo.entities.Author;
import com.graphQl.demo.entities.Book;
import com.graphQl.demo.entities.Review;
import com.graphQl.demo.repository.AuthorRepository;
import com.graphQl.demo.repository.BookRepository;
import com.graphQl.demo.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyData implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final AuthorRepository authorRepository;

    public DummyData(BookRepository bookRepository, ReviewRepository reviewRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(authorRepository.count() == 0) {
            Author author = Author.builder()
                    .name("Paulo Coelho")
                    .email("paulo@yahoo.com")
                    .build();
            authorRepository.save(author);
            Author author1 = Author.builder()
                    .name("William Peter Blatty")
                    .email("perter@h=gmail.com")
                    .build();
            authorRepository.save(author1);

            Book book = Book.builder()
                    .bookName("The Alchemist")
                    .isbn("0061122416")
                    .author(author)
                    .build();
            bookRepository.save(book);
            Book book1 = Book.builder()
                    .bookName("The Exorcist")
                    .isbn("003332416")
                    .author(author1)
                    .build();
            bookRepository.save(book1);

            Review review = Review.builder()
                    .review("This is a great book")
                    .book(book)
                    .build();
            Review review1 = Review.builder()
                    .review("This is a great book")
                    .book(book1)
                    .build();

            Review review2 = Review.builder()
                    .review("This is a great book")
                    .book(book1)
                    .build();

            Review review3 = Review.builder()
                    .review("This is a great book")
                    .book(book1)
                    .build();

            reviewRepository.save(review);
            reviewRepository.save(review1);
            reviewRepository.save(review2);
            reviewRepository.save(review3);
        }



    }
}
