package com.gonchcharov.ru.EPAMFinalProject.service;

import com.gonchcharov.ru.EPAMFinalProject.entity.*;
import com.gonchcharov.ru.EPAMFinalProject.user.CrmUser;

import java.util.List;

public interface StudentService {
   // public void save(int idCourse);

    public Student findStudentById(int id);

    public void save(Student student);

    public void save(Document document);

}
