package com.LibraryApp2.demo.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TeacherRegRepository extends JpaRepository<TeacherRegModel,Integer>
{
    Optional<TeacherRegModel> findFirstByTeacherName(String teacherName);

    Optional<TeacherRegModel> findByTeacherNameAndPassword(String teacherName, String password);


}
