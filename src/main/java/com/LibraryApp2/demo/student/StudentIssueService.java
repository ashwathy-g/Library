package com.LibraryApp2.demo.student;

import com.LibraryApp2.demo.dto.StudentIssueRequest;
import com.LibraryApp2.demo.lib.LibModel;
import com.LibraryApp2.demo.lib.LibRepository;
import com.LibraryApp2.demo.teacher.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@Service
@Slf4j
public class StudentIssueService
{
    @Autowired
    LibRepository libRepository;

    @Autowired
    TeacherRegRepository regRepository;
    @Autowired
    StudentIssueRepository studentIssueRepository;
    @Autowired
    TeacherIssueRepository teacherIssueRepository;
@Autowired
    TeacherBookRepository teacherBookRepository;
    @org.springframework.transaction.annotation.Transactional
    public StudentIssueRequest issueBookToStudent(StudentIssueRequest request) {
        if (request.getBookName() == null || request.getTeacherId() == null) {
            throw new IllegalArgumentException("BooK name and Teacher ID must not be null");

        }
        List<LibModel> books = libRepository.findByBookName(request.getBookName());
        if (books.isEmpty()) {
            throw new IllegalArgumentException("Book not found with the given name");

        }
        LibModel book = books.get(0);
        TeacherRegModel teacher = regRepository.findById(request.getTeacherId()).orElse(null);

        if (teacher == null) {
            throw new IllegalArgumentException("Invalid teacher ID");
        }
        StudentIssueModel issueModel = new StudentIssueModel();
        issueModel.setBook(book);
        issueModel.setTeacher(teacher);
        issueModel.setStudentName(request.getStudentName());
        issueModel.setStandard(request.getStandard());
        issueModel.setDivision(request.getDivision());
        issueModel.setDateOfIssue(LocalDate.now());
        issueModel.setDateOfReturn(LocalDate.now().plusDays(14));
        studentIssueRepository.save(issueModel);
        TeacherBookModel teacherBookModel = teacherBookRepository.findByBookIdAndTeacherId(book.getId(), teacher.getId());
        if (teacherBookModel != null && teacherBookModel.getQuantity() > 0) {
            teacherBookModel.setQuantity(teacherBookModel.getQuantity() - 1);
            teacherBookRepository.save(teacherBookModel);
        } else {
            throw new IllegalArgumentException("Book quantity not available for teacher");
        }
        StudentIssueRequest response = new StudentIssueRequest();
        response.setMessage("Book issued successfully.");
        response.setStudentName(issueModel.getStudentName());
        response.setBookId(issueModel.getBook().getId());
        response.setBookName(issueModel.getBook().getBookName());
        response.setTeacherId(issueModel.getTeacher().getId());
        response.setTeacherName(issueModel.getTeacher().getTeacherName());
        response.setStandard(issueModel.getStandard());
        response.setDivision(issueModel.getDivision());
        response.setDateOfIssue(issueModel.getDateOfIssue());
        response.setDateOfReturn(issueModel.getDateOfReturn());

        return response;


    }


    @Transactional
    public ResponseEntity<String> getAllIssuedBooks() {

        log.info("Inside getAllIssuedBooks");

        List<StudentIssueModel> issuedBooks = studentIssueRepository.findAll();
        JsonArray jsonArray = new JsonArray();

        for (StudentIssueModel issue : issuedBooks) {
            JsonObject bookDetails = new JsonObject();
            bookDetails.addProperty("bookName", issue.getBook().getBookName());
            bookDetails.addProperty("bookId",issue.getBook().getId());
            bookDetails.addProperty("issueDate", issue.getDateOfIssue().toString());
            bookDetails.addProperty("returnDate", issue.getDateOfReturn().toString());
            bookDetails.addProperty("studentName", issue.getStudentName());
            bookDetails.addProperty("standard",issue.getStandard());
            bookDetails.addProperty("division",issue.getDivision());
            String teacherName=issue.getTeacher().getTeacherName();
            bookDetails.addProperty("teacherName",teacherName);
            jsonArray.add(bookDetails);
        }
        return ResponseEntity.ok(jsonArray.toString());
    }




}
