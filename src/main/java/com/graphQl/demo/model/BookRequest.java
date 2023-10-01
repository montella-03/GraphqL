package com.graphQl.demo.model;

public record BookRequest(String bookName,String isbn,Long authorId) {
}
