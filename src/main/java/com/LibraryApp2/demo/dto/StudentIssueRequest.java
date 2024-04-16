package com.LibraryApp2.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
@Data
public class StudentIssueRequest
{
    private String message;
    private String bookName;
    private Integer bookId;
    private Integer teacherId;
    private String studentName;
    private String standard;
    private String division;
    private String teacherName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfReturn;
}
