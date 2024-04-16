package com.LibraryApp2.demo.dto;

import lombok.Data;

import java.util.List;
@Data
public class TeacherIssueResponse
{
    private String message;
    private Integer teacherId;
    private String teacherName;
    private List<String> bookNames;
}
