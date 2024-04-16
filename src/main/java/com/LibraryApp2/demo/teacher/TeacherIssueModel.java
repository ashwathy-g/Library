package com.LibraryApp2.demo.teacher;

import com.LibraryApp2.demo.lib.LibModel;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "issuedBooksToTeacher")
@Component
public class TeacherIssueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private LibModel book;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherRegModel teacher;


    @Column(name = "date_of_return")
    private LocalDate dateOfReturn;

    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    public String getTeacherName() {
        if (this.teacher != null) {
            return this.teacher.getTeacherName();
        }
        return null; // Or any default value you prefer
    }
    public List<String> getBooksTakenByTeacher(Integer teacherId) {
        List<String> bookNames = new ArrayList<>();
        if (this.teacher != null && this.teacher.getId().intValue() == teacherId.intValue()) {
            if (this.book != null) {
                bookNames.add(this.book.getBookName());
            }
        }
        return bookNames;

    }
}




