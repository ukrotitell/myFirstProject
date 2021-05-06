package com.gonchcharov.ru.EPAMFinalProject.dao;

import com.gonchcharov.ru.EPAMFinalProject.entity.Student;
import com.gonchcharov.ru.EPAMFinalProject.entity.Teacher;

public interface TeacherDAO {

    public Teacher findTeacherById(int id);

    public void save(Teacher teacher);


}
