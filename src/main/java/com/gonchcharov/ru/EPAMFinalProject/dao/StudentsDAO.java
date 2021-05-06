package com.gonchcharov.ru.EPAMFinalProject.dao;

import com.gonchcharov.ru.EPAMFinalProject.entity.Courses;
import com.gonchcharov.ru.EPAMFinalProject.entity.Document;
import com.gonchcharov.ru.EPAMFinalProject.entity.Student;
import com.gonchcharov.ru.EPAMFinalProject.entity.User;

import java.util.List;

public interface StudentsDAO {

    Student findStudentById(int id);


    void save(Student student);

    void save(Document document);

}
