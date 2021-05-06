package com.gonchcharov.ru.EPAMFinalProject.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "teacher")
    private List<Courses> courses;



    public Teacher() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Courses> getCourse() {
        return courses;
    }

    public void setCourse(List<Courses> course) {
        this.courses = course;
    }

    public void addCourse(Courses tempCourse){
        if (courses == null){
            courses = new ArrayList<>();
        }
        courses.add(tempCourse);
        tempCourse.setTeacher(this);
    }
}