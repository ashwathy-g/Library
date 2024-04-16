package com.LibraryApp2.demo.dto;

import lombok.Data;

@Data
public class BookResponse
{
    private Integer bookId;
    private String bookName;
    private String authorName;
    private String message;
    public BookResponse(){

    }

    public BookResponse(Integer bookId, String bookName, String authorName) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public BookResponse(String message) {
        this.message = message;

    }

}
