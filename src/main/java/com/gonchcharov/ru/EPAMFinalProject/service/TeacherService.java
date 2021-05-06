package com.gonchcharov.ru.EPAMFinalProject.service;

import com.gonchcharov.ru.EPAMFinalProject.entity.Teacher;

public interface TeacherService {

    public Teacher findTeacherById(int id);

    public void save(Teacher teacher);

}
