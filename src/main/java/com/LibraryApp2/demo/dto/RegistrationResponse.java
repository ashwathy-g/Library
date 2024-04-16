package com.LibraryApp2.demo.dto;

import lombok.Data;

@Data
public class RegistrationResponse
{
    private Integer id;
    private String teacherName;
    private String email;
    private String role;
    private String standard;
    private String division;
    private String subject;
    private String message;
}
