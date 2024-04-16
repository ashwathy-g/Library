package com.LibraryApp2.demo.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ReturnedBookResponse
{
    private Long bookId;
    private String bookName;
    private Long teacherId;
    private String teacherName;
    private LocalDate returnDate;
}
