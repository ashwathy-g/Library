package com.LibraryApp2.demo.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/return")
public class TeacherReturnController {
    @Autowired
    private TeacherReturnService teacherReturnService;

    @PostMapping("/return-book")
    public ResponseEntity<String> returnBook(@RequestParam Integer bookId, @RequestParam Integer userId) {
        boolean returned = teacherReturnService.returnBook(bookId, userId);
        if (returned) {
            return ResponseEntity.ok("Book Returned Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed To Return Book");
        }
    }

    @GetMapping("/all-return-books")
    public ResponseEntity<String> getAllReturnedBooks() {
        return teacherReturnService.getAllReturnedBooks();

    }
}
