package com.gonchcharov.ru.EPAMFinalProject.entity;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private long size;


    @Column(name = "content")
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "lab_id")
    private Labs lab;

    public Student getStudents() {
        return student;
    }

    public void setStudents(Student student) {
        this.student = student;
    }

    public Document() {
    }

    public Document(int id, String name, long size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public Document(int id, String name, long size, Student student) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Labs getLab() {
        return lab;
    }

    public void setLab(Labs lab) {
        this.lab = lab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }


    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) throws SizeLimitExceededException {

        this.content = content;
    }
}