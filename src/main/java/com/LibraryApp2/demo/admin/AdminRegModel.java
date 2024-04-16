package com.LibraryApp2.demo.admin;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ADMIN")
public class AdminRegModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "adminName")
    private String adminName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name = "role")
    private String role;

}
