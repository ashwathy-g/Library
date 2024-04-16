package com.LibraryApp2.demo.teacher;

import com.LibraryApp2.demo.dto.IssueDTO;
import com.LibraryApp2.demo.lib.LibModel;
import com.LibraryApp2.demo.lib.LibRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TeacherIssueService {
    @Autowired
    LibRepository libRepository;
    @Autowired
    TeacherRegRepository regRepository;
    @Autowired
    TeacherIssueRepository teacherIssueRepository;
    @Autowired
    TeacherBookRepository teacherBookRepository;

    @Transactional
    public boolean issueBook(Integer bookId, Integer userId) {
        log.info("inside issueBook");
        LibModel book = libRepository.findById(bookId).orElse(null);
        TeacherRegModel teacher = regRepository.findById(userId).orElse(null);

        if (book != null && teacher != null && Integer.parseInt(book.getQuantity()) > 0) {
            log.info("Book found and available, quantity before update: {}", book.getQuantity());

            int quantity = Integer.parseInt(book.getQuantity());
            if (quantity>0)
            book.setQuantity(String.valueOf(quantity - 1));
            libRepository.save(book);
            log.info("Book quantity after update: {}", book.getQuantity());
            TeacherIssueModel teacherIssueModel = new TeacherIssueModel();
            teacherIssueModel.setBook(book);
            teacherIssueModel.setTeacher(teacher);
            teacherIssueModel.setDateOfIssue(LocalDate.now());
            teacherIssueModel.setDateOfReturn(LocalDate.now().plusDays(28));
            teacherIssueRepository.save(teacherIssueModel);
            TeacherBookModel teacherBookModel = teacherBookRepository.findByBookIdAndTeacherId(bookId, userId);
            if (teacherBookModel != null) {
                // If the book is already in TeacherBookModel, update the quantity
                teacherBookModel.setQuantity(teacherBookModel.getQuantity() + 1); // Assuming quantity increases by 1
            } else {
                // If the book is not in TeacherBookModel, create a new entry
                teacherBookModel = new TeacherBookModel();
                teacherBookModel.setBookId(bookId);
                teacherBookModel.setTeacherId(userId);
                teacherBookModel.setQuantity(1); // Assuming initial quantity is 1
            }
            teacherBookRepository.save(teacherBookModel);


            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public ResponseEntity<String> getAllIssuedBooks() {
        log.info("inside getAllIssuedBooks");
        List<TeacherIssueModel> issuedBooks = teacherIssueRepository.findAll();
        JsonArray jsonArray = new JsonArray();
        for (TeacherIssueModel issue : issuedBooks) {
            JsonObject bookDetails = new JsonObject();
            bookDetails.addProperty("bookId", issue.getBook().getId());
            bookDetails.addProperty("bookName", issue.getBook().getBookName());
            bookDetails.addProperty("issueDate", issue.getDateOfIssue().toString());
            LocalDate returnDate = issue.getDateOfReturn();
            if (returnDate != null) {
                bookDetails.addProperty("returnDate", returnDate.toString());
            } else {
                bookDetails.addProperty("returnDate", "N/A");


            }
            bookDetails.addProperty("teacherId", issue.getTeacher().getId());
            bookDetails.addProperty("teacherName", issue.getTeacherName());
            bookDetails.addProperty("class", issue.getTeacher().getStandard());
            bookDetails.addProperty("division", issue.getTeacher().getDivision());
            bookDetails.addProperty("subject", issue.getTeacher().getSubject());
            TeacherBookModel teacherBook = teacherBookRepository.findByBookIdAndTeacherId(issue.getBook().getId(), issue.getTeacher().getId());
            if (teacherBook != null) {
                bookDetails.addProperty("quantity", teacherBook.getQuantity());
            } else {
                bookDetails.addProperty("quantity", 0); // Default value if quantity not found
            }

            jsonArray.add(bookDetails);
        }
        return ResponseEntity.ok(jsonArray.toString());
    }

    public List<String> getBooksTakenByTeacher(Integer teacherId) {
        List<String> bookNames = new ArrayList<>();
        List<TeacherIssueModel> issuedBooks = teacherIssueRepository.findByTeacherId(teacherId);
        log.info("issuedBooks: "+issuedBooks);
        for (TeacherIssueModel issuedBook : issuedBooks) {
            if (issuedBook.getBook() != null) {
                bookNames.add(issuedBook.getBook().getBookName());
                bookNames.add(issuedBook.getDateOfIssue().toString());
                bookNames.add(issuedBook.getDateOfReturn().toString());
                bookNames.add(issuedBook.getBook().getId().toString());

                log.info("book : "+issuedBook.getBook().getBookName());
            }
        }
        return bookNames;
    }



}
