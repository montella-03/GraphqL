package com.graphQl.demo.controllers;

import com.graphQl.demo.entities.Author;
import com.graphQl.demo.repository.AuthorRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/author")
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @QueryMapping
    public Iterable<Author> authors(){
        return authorRepository.findAll();
    }
    @MutationMapping
    public Author createAuthor(@Argument String name, @Argument String email){
        Author author = Author.builder()
                .name(name)
                .email(email)
                .build();
        return authorRepository.save(author);
    }
}
