package com.gonchcharov.ru.EPAMFinalProject.service;

import com.gonchcharov.ru.EPAMFinalProject.dao.CoursesDAO;
import com.gonchcharov.ru.EPAMFinalProject.entity.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    private CoursesDAO coursesDAO;


    @Override
    @Transactional
    public List<Courses> findAll() {
        return coursesDAO.findAll();
    }

    @Override
    @Transactional
    public Courses findById(int id) {

        return coursesDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Courses course) {
        coursesDAO.save(course);
    }


}





