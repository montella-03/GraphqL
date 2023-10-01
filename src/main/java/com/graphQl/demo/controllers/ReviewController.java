package com.graphQl.demo.controllers;

import com.graphQl.demo.entities.Book;
import com.graphQl.demo.entities.Review;
import com.graphQl.demo.repository.BookRepository;
import com.graphQl.demo.repository.ReviewRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/review")
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewController(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }
    @QueryMapping
    public Iterable<Review> reviews(){
        return reviewRepository.findAll();
    }
    @MutationMapping
    public Review createReview(@Argument String review, @Argument Long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(
                ()-> new RuntimeException("Book not found")
        );
        Review review1 = Review.builder()
                .review(review)
                .book(book)
                .build();
        return reviewRepository.save(review1);
    }
}
