package com.LibraryApp2.demo.teacher;

import com.LibraryApp2.demo.lib.LibModel;
import com.LibraryApp2.demo.lib.LibRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@Service
public class TeacherReturnService
{
    @Autowired
    private LibRepository libRepository;
    @Autowired
    private TeacherRegRepository regRepository;
    @Autowired
    TeacherReturnRepository teacherReturnRepository;
    @Autowired
    private TeacherRegRepository teacherRegRepository;
    @Autowired
    TeacherIssueRepository teacherIssueRepository;
    @Autowired
    TeacherBookRepository teacherBookRepository;


    @Transactional
    public boolean returnBook(Integer bookId, Integer userId) {
        log.info("inside returnBook");
        LibModel book = libRepository.findById(bookId).orElse(null);
        TeacherRegModel teacher = teacherRegRepository.findById(userId).orElse(null);
        if (book != null && teacher != null) {
            TeacherReturnModel teacherReturnModel = new TeacherReturnModel();
            teacherReturnModel.setBook(book);
            teacherReturnModel.setTeacher(teacher);
            teacherReturnModel.setReturnDate(LocalDate.now());
            teacherReturnRepository.save(teacherReturnModel);
            int currentQuantity = Integer.parseInt(book.getQuantity());
            book.setQuantity(String.valueOf(currentQuantity + 1));
            libRepository.save(book);

            List<TeacherIssueModel> issuedBooks = teacherIssueRepository.findByBookIdAndTeacherId(bookId, userId);
            if (!issuedBooks.isEmpty()) {
                teacherIssueRepository.delete(issuedBooks.get(0)); // Assuming there's only one entry for the book issued to the teacher
            }
            TeacherBookModel teacherBookModel = teacherBookRepository.findByBookIdAndTeacherId(bookId, userId);
            if (teacherBookModel != null) {
                teacherBookRepository.delete(teacherBookModel);
            }


            log.info("Book returned successfully");
            return true;
        } else {
            log.error("Failed to return book");
            return false;

        }
    }

    @Transactional
    public ResponseEntity<String> getAllReturnedBooks() {
        log.info("inside getAllReturnedBooks");
        List<TeacherReturnModel> returnedBooks = teacherReturnRepository.findAll();
        JsonArray jsonArray = new JsonArray();
        for (TeacherReturnModel returnedBook : returnedBooks) {
            JsonObject bookDetails = new JsonObject();
            bookDetails.addProperty("bookId", returnedBook.getBook().getId());
            bookDetails.addProperty("bookName", returnedBook.getBook().getBookName());
            bookDetails.addProperty("returnDate", returnedBook.getReturnDate().toString());
            bookDetails.addProperty("teacherId", returnedBook.getTeacher().getId());
            bookDetails.addProperty("teacherName", returnedBook.getTeacher().getTeacherName());
            // Add more details as needed

            jsonArray.add(bookDetails);
        }
        return ResponseEntity.ok(jsonArray.toString());
    }
}




