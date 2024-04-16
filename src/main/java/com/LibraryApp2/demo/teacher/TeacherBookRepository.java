package com.LibraryApp2.demo.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherBookRepository extends JpaRepository<TeacherBookModel,Integer> {
   TeacherBookModel findByBookIdAndTeacherId(Integer bookId, Integer userId);

   List<TeacherBookModel> findByBookIdAndTeacherIdNot(Integer bookId, Integer userId);
}
