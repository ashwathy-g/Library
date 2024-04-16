package com.LibraryApp2.demo.teacher;

import com.LibraryApp2.demo.lib.LibModel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name="Teacher_return_model")

public class TeacherReturnModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private LibModel book;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherRegModel teacher;

    @Column(name = "return_date")
    private LocalDate returnDate;

}
