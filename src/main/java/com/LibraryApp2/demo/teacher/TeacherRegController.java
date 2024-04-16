package com.LibraryApp2.demo.teacher;

import com.LibraryApp2.demo.dto.RegistrationResponse;
import com.LibraryApp2.demo.dto.TeacherIssueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api")
public class TeacherRegController
{
    @Autowired
    TeacherRegService regService;
    @Autowired
    TeacherIssueModel issueModel;

    @PostMapping(path = "/REG")
    public RegistrationResponse registerTeacher(@RequestBody TeacherRegModel teacher) {
        return regService.registerUser(
                teacher.getTeacherName(),
                teacher.getPassword(),
                teacher.getEmail(),
                teacher.getRole(),
                teacher.getStandard(),
                teacher.getDivision(),
                teacher.getSubject()
        );
    }


    @PostMapping(path = "/userl")
    public ResponseEntity<?> login(@RequestBody TeacherRegModel regModel) {
        TeacherRegModel authenticated = regService.authenticate(regModel.getTeacherName(), regModel.getPassword());
        if (authenticated != null) {
            Integer teacherId = authenticated.getId();
            List<String> bookNames = regService.getBooksTakenByTeacher(teacherId);
            TeacherIssueResponse response=new TeacherIssueResponse();
            response.setTeacherId(teacherId);
            response.setBookNames(bookNames);
            response.setMessage("Login Successfully");
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Message:Invalid credentials");
        }

    }


    @PutMapping("/{id}")
    public String updateTeacherDetails(@PathVariable Integer id, @RequestBody TeacherRegModel updatedTeacher) {
        return regService.updateTeacherDetails(id, updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Integer id) {
        return regService.deleteTeacher(id);
    }


    @GetMapping("/allTeachers")
    public List<TeacherRegModel> getAllTeachers() {
        return regService.getAllTeachers();
    }


}
