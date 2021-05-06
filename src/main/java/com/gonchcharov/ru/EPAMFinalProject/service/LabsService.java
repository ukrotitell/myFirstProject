package com.gonchcharov.ru.EPAMFinalProject.service;

import com.gonchcharov.ru.EPAMFinalProject.entity.Labs;

import java.util.List;

public interface LabsService {
    public List<Labs> findLabsByCourseId(int id);

    public void save(List<Labs> labs);

    public Labs findLabsId(int id) ;
}
