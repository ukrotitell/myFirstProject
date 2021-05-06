package com.gonchcharov.ru.EPAMFinalProject.service;

import com.gonchcharov.ru.EPAMFinalProject.dao.StudentsDAO;
import com.gonchcharov.ru.EPAMFinalProject.entity.*;
import com.gonchcharov.ru.EPAMFinalProject.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentsDAO studentsDAO;

    @Override
    @Transactional
    public Student findStudentById(int id) {
        return studentsDAO.findStudentById(id);
    }

    @Override
    @Transactional
    public void save(Student student) {
        studentsDAO.save(student);
    }

    @Override
    @Transactional
    public void save(Document document) {
        studentsDAO.save(document);
    }



}
