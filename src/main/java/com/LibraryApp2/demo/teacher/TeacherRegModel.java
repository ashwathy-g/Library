package com.LibraryApp2.demo.teacher;

import com.LibraryApp2.demo.lib.LibModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "TeacherRegModel")
public class TeacherRegModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "teacherName")
    private  String teacherName;

    @Column(name = "password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "standard")
    private String standard;

    @Column(name = "division")
    private String division;

    @Column(name="subject")
    private String subject;


    @OneToMany(mappedBy = "userDetails",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<LibModel> booksTaken;

}
