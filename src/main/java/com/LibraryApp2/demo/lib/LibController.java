package com.LibraryApp2.demo.lib;

import com.LibraryApp2.demo.dto.BookResponse;
import com.LibraryApp2.demo.student.StudentIssueRepository;
import com.LibraryApp2.demo.teacher.TeacherRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/library")
public class LibController {
    @Autowired
    LibService libService;

    @Autowired
    TeacherRegService regService;
    @Autowired
    StudentIssueRepository studentIssueRepository;

    @PostMapping(path = "/Add")
    public ResponseEntity<BookResponse> addBook(@ModelAttribute LibModel libModel, @RequestParam("imageFile") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            return ResponseEntity.badRequest().body(new BookResponse("Image file is required ."));
        }
        try {
            byte[] imageData = imageFile.getBytes();
            libModel.setImage(imageData);
            LibModel savedBook = libService.addBookWithImage(libModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse(savedBook.getId(), savedBook.getBookName(), savedBook.getAuthorName()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BookResponse("Failed to add book with image."));
        }


    }

    @GetMapping(path = "/getAllBooks")
    public ResponseEntity<List<LibModel>> getAllBooks() {
        List<LibModel> allBooks = libService.getAllBooks();
        if (!allBooks.isEmpty()) {
            return ResponseEntity.ok(allBooks);

        } else {
            return ResponseEntity.notFound().build();

        }


    }
    @PutMapping(path = "/update")
    public String update(@RequestBody LibModel libModel) {
        return libService.updateDetails(libModel);

    }

    @DeleteMapping(path = "deleteId/{id}")
    public String deleteDetails(@PathVariable Integer id) {
        libService.deleteDetails(id);
        return "deleted";

    }

    @GetMapping(path = "/searchByAuthor")
    public List<LibModel> searchBooksByAuthorName(@RequestParam String authorName) {
        return libService.findBooksByAuthorName(authorName);
    }

    @GetMapping(path = "/searchByLanguage")
    public List<LibModel> searchBooksByLanguage(@RequestParam String language) {
        return libService.findBooksByLanguage(language);
    }

    @GetMapping(path = "/searchByCategory")
    public List<LibModel> getBooksByCategory(@RequestParam String category) {
        return libService.findByCategory(category);
    }

}