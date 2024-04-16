package com.LibraryApp2.demo.lib;

import com.LibraryApp2.demo.teacher.TeacherRegModel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="libmodel")
public class LibModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "bookName")
    private String bookName;

    @Column(name = "authorName")
    private String authorName;

    @Column(name = "category")
    private String category;


    @Column(name = "language")
    private String language;

    @Column(name = "rack")
    private String Rack;

    @Column(name = "quantity")
    private String quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private TeacherRegModel userDetails;


    @Column(name = "image",columnDefinition = "bytea")
    private byte[]image;


}
