package com.LibraryApp2.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/student_return")
public class StudentReturnController
{
    @Autowired
    StudentReturnService studentReturnService;
    @PostMapping("/return-book-by-student")
    public ResponseEntity<String> returnBookByStudent(@RequestParam Integer bookId, @RequestParam String studentName) {
        boolean returned = studentReturnService.returnBookByStudentName(bookId, studentName);
        if (returned) {
            return ResponseEntity.ok("Book Returned Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed To Return Book");
        }
    }
}
