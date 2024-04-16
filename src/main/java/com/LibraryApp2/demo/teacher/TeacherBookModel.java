package com.LibraryApp2.demo.teacher;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "BookModel")
public class TeacherBookModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "quantity")
    private Integer quantity;


}
