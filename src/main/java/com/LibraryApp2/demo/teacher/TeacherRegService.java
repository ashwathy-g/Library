package com.LibraryApp2.demo.teacher;

import com.LibraryApp2.demo.dto.RegistrationResponse;
import com.LibraryApp2.demo.lib.LibRepository;
import com.LibraryApp2.demo.student.StudentIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherRegService
{
    @Autowired
    TeacherRegRepository regRepository;
    @Autowired
    LibRepository libRepository;
    @Autowired
    TeacherIssueModel issueModel;
    @Autowired
    StudentIssueRepository studentIssueRepository;
    public RegistrationResponse registerUser(String teacherName, String password, String email, String role, String standard, String division, String subject)   {
        RegistrationResponse response=new RegistrationResponse();
        if (teacherName == null && password ==null){
            response.setMessage("Invalid registration details");
            return response;
        }else {
            if (regRepository.findFirstByTeacherName(teacherName).isPresent()){
                response.setMessage("Duplicate login");
                return response;
            }
            TeacherRegModel regModel=new TeacherRegModel();
            regModel.setTeacherName(teacherName);
            regModel.setPassword(password);
            regModel.setEmail(email);
            regModel.setRole(role);
            regModel.setStandard(standard);
            regModel.setDivision(division);
            regModel.setSubject(subject);
            TeacherRegModel savedTeacher=regRepository.save(regModel);
            response.setId(savedTeacher.getId());
            response.setTeacherName(savedTeacher.getTeacherName());
            response.setEmail(savedTeacher.getEmail());
            response.setRole(savedTeacher.getRole());
            response.setStandard(savedTeacher.getStandard());
            response.setDivision(savedTeacher.getDivision());
            response.setSubject(savedTeacher.getSubject());
            response.setMessage("Registration successful");
            return response;

        }
    }
    public TeacherRegModel authenticate(String teacherName, String password)
    {
        return regRepository.findByTeacherNameAndPassword(teacherName, password).orElse(null);
    }
    public String updateTeacherDetails(Integer id,TeacherRegModel updatedTeacher)

    {
        TeacherRegModel existingTeacher=regRepository.findById(id).orElse(null);
        if (existingTeacher !=null)
        {
            if (updatedTeacher.getTeacherName()!=null){
                existingTeacher.setTeacherName(updatedTeacher.getTeacherName());
            }
            if (updatedTeacher.getPassword()!=null)
            {
                existingTeacher.setPassword(updatedTeacher.getPassword());
            }
            if (updatedTeacher.getEmail()!=null)
            {
                existingTeacher.setEmail(updatedTeacher.getEmail());
            }
            if (updatedTeacher.getRole()!=null)
            {
                existingTeacher.setRole(updatedTeacher.getRole());
            }
            if (updatedTeacher.getStandard()!=null)
            {
                existingTeacher.setStandard(updatedTeacher.getStandard());
            }
            if (updatedTeacher.getDivision()!=null)
            {
                existingTeacher.setDivision(updatedTeacher.getDivision());
            }
            if (updatedTeacher.getSubject()!=null)
            {
                existingTeacher.setSubject(updatedTeacher.getSubject());
            }
            regRepository.save(existingTeacher);
            return "Details updated successfully";
        }
        return "Failed to update details";
    }
    public String deleteTeacher(Integer id) {
        if (regRepository.existsById(id)) {
            regRepository.deleteById(id);
            return "Teacher details deleted successfully";
        } else {
            return "Failed to delete teacher details.Teacher not found.";
        }
    }
    public List<TeacherRegModel> getAllTeachers()
    {
        return regRepository.findAll();
    }
    public List<String>getBooksTakenByTeacher(Integer teacherId)
    {
        return issueModel.getBooksTakenByTeacher(teacherId);
    }




}


