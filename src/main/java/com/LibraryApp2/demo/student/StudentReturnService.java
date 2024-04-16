package com.LibraryApp2.demo.student;

import com.LibraryApp2.demo.teacher.TeacherBookModel;
import com.LibraryApp2.demo.teacher.TeacherBookRepository;
import com.LibraryApp2.demo.teacher.TeacherIssueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
@Service
public class StudentReturnService
{
    @Autowired
    StudentIssueRepository studentIssueRepository;
    @Autowired
    StudentReturnRepository studentReturnRepository;
    @Autowired
    TeacherBookRepository teacherBookRepository;
    @Autowired
    TeacherIssueModel teacherIssueModel;

    @Transactional
    public boolean returnBookByStudentName(Integer bookId, String studentName) {
        StudentIssueModel issuedBook = studentIssueRepository.findByBookIdAndStudentName(bookId, studentName);
        if (issuedBook != null) {
            StudentReturnModel returnedBook = new StudentReturnModel();
            returnedBook.setBook(issuedBook.getBook());
            returnedBook.setStudentName(studentName);
            returnedBook.setReturnDate(LocalDate.now());

            // Save the returned book entry and delete from issued books
            studentReturnRepository.save(returnedBook);
            studentIssueRepository.delete(issuedBook);
            TeacherBookModel teacherBookModel=teacherBookRepository.findByBookIdAndTeacherId(bookId,issuedBook.getTeacher().getId());
            if (teacherBookModel!=null) {
                teacherBookModel.setQuantity(teacherBookModel.getQuantity() + 1);
                teacherBookRepository.save(teacherBookModel);
                return true;
            }
            else {
                teacherBookModel=new TeacherBookModel();
                teacherBookModel.setBookId(bookId);
                teacherBookModel.setTeacherId(issuedBook.getTeacher().getId());
                teacherBookModel.setQuantity(1); // Assuming initial quantity is 1
                teacherBookRepository.save(teacherBookModel); // Save the new TeacherBookModel entry
                return true;
            }
        } else {
            return false;

            }
            }
            }
