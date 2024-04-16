package com.LibraryApp2.demo.student;

import com.LibraryApp2.demo.lib.LibModel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "student_return_model")
public class StudentReturnModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private LibModel book;


    @Column(name = "studentName")
    private String studentName;

    @Column(name = "return_date")
    private LocalDate returnDate;

}
