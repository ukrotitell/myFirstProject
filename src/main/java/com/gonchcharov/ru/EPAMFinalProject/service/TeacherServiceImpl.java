package com.gonchcharov.ru.EPAMFinalProject.service;

import com.gonchcharov.ru.EPAMFinalProject.dao.TeacherDAO;
import com.gonchcharov.ru.EPAMFinalProject.dao.UserDao;
import com.gonchcharov.ru.EPAMFinalProject.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherDAO teacherDao;

    @Override
    @Transactional
    public Teacher findTeacherById(int id) {
        return teacherDao.findTeacherById(id);
    }

    @Override
    @Transactional
    public void save(Teacher teacher) {
        teacherDao.save(teacher);
    }
}
