package com.gonchcharov.ru.EPAMFinalProject.service;

import com.gonchcharov.ru.EPAMFinalProject.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CoursesService  {
    public List<Courses> findAll();

    Courses findById(int id);

    public void save(Courses course);

}
