package com.LibraryApp2.demo.teacher;

import com.LibraryApp2.demo.dto.IssueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/issues")
public class TeacherIssueController
{
    @Autowired
    TeacherIssueService teacherIssueService;

    @PostMapping("/issue-book")
    public ResponseEntity<String> issueBook(@RequestParam(name="bookId") Integer bookId, @RequestParam(name="userId") Integer userId) {
        boolean issued = teacherIssueService.issueBook(bookId, userId);
        if (issued) {
            return ResponseEntity.ok("Book Issued Successfully");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed To Issue Book");
        }
    }

    @GetMapping("/allissuedbooks")
    public ResponseEntity<String> getAllIssuedBooks() {
        return teacherIssueService.getAllIssuedBooks();
    }
    @GetMapping("/issued_book_teacher")
    public ResponseEntity<List<String>> getBooksIssuedToTeacher(@RequestParam Integer teacherId)
    { List<String> booksTakenByTeacher = teacherIssueService.getBooksTakenByTeacher(teacherId);
        return ResponseEntity.ok(booksTakenByTeacher);
    }







}
