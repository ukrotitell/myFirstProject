package com.gonchcharov.ru.EPAMFinalProject.dao;

import com.gonchcharov.ru.EPAMFinalProject.entity.Labs;
import com.gonchcharov.ru.EPAMFinalProject.entity.Student;

import java.util.Collection;
import java.util.List;

public interface LabsDAO {
    public List<Labs> findLabsByCourseId(int id) ;

    public void save(List<Labs> labs);

    public Labs findLabsId(int id) ;
}
