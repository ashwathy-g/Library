package com.LibraryApp2.demo.teacher;

import com.LibraryApp2.demo.lib.LibModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherIssueRepository extends JpaRepository<TeacherIssueModel,Integer>
{
    List<TeacherIssueModel> findByTeacher(TeacherRegModel teacher);

    List<TeacherIssueModel> findByTeacherId(Integer teacherId);

    List<TeacherIssueModel> findByBookIdAndTeacherId(Integer bookId, Integer userId);

    TeacherIssueModel findByBookAndTeacher(LibModel book, TeacherRegModel teacher);

}
