package com.graphQl.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private String review;
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;
}
