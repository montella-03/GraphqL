package com.graphQl.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bookName;
    private String isbn;
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Review> reviews= new ArrayList<>();

}
