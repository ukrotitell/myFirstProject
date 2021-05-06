package com.gonchcharov.ru.EPAMFinalProject.dao;

import com.gonchcharov.ru.EPAMFinalProject.entity.Courses;
import com.gonchcharov.ru.EPAMFinalProject.entity.Labs;
import com.gonchcharov.ru.EPAMFinalProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CoursesDAO  {
    public List<Courses> findAll();

    Courses findById(int id);

    public void save(Courses course);
}
