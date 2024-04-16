package com.LibraryApp2.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentIssueRepository extends JpaRepository<StudentIssueModel,Integer>
{
    StudentIssueModel findByBookIdAndStudentName(Integer bookId, String studentName);


    List<StudentIssueModel> findByStudentName(String studentName);


}
