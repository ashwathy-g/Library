package com.LibraryApp2.demo.student;

import com.LibraryApp2.demo.lib.LibModel;
import com.LibraryApp2.demo.teacher.TeacherIssueModel;
import com.LibraryApp2.demo.teacher.TeacherRegModel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "bookIssuedToStudents")
public class StudentIssueModel
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


    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    @Column(name = "date_of_return")
    private LocalDate dateOfReturn;

    @Column(name = "student_name")
    private String studentName;

    @Column(name="standard")
    private String standard;

    @Column(name = "division")
    private String division;

    public void setTeacher(TeacherRegModel teacher) { this.teacher=teacher;
    }


}
