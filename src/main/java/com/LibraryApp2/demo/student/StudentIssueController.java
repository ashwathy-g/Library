package com.LibraryApp2.demo.student;

import com.LibraryApp2.demo.dto.StudentIssueRequest;
import com.LibraryApp2.demo.teacher.TeacherIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/student_issues")

public class StudentIssueController
{
    @Autowired
    StudentIssueService studentIssueService;
    @Autowired
    TeacherIssueService teacherIssueService;

    @PostMapping("/issue_book")
    public ResponseEntity<StudentIssueRequest> issueBookToStudent(@RequestBody StudentIssueRequest request) {
        try {
            if (request.getBookName() == null || request.getTeacherId() == null) {
                throw new IllegalArgumentException("Book name and Teacher ID must not be null");

            }
            StudentIssueRequest response = studentIssueService.issueBookToStudent(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            StudentIssueRequest errorResponse = new StudentIssueRequest();
            errorResponse.setMessage("Error issuing book:" + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            StudentIssueRequest errorResponse = new StudentIssueRequest();
            errorResponse.setMessage("Error issuing book:" + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/issued-books")
    public ResponseEntity<String> getAllIssuedBooks() {
        try {
            return studentIssueService.getAllIssuedBooks();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving issued books: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
