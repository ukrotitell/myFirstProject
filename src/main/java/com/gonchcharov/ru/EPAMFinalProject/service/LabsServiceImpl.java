package com.gonchcharov.ru.EPAMFinalProject.service;

import com.gonchcharov.ru.EPAMFinalProject.dao.LabsDAO;
import com.gonchcharov.ru.EPAMFinalProject.entity.Labs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class LabsServiceImpl implements LabsService{

    @Autowired
    private LabsDAO labsDAO;

    @Override
    @Transactional
    public List<Labs> findLabsByCourseId(int id) {
        return labsDAO.findLabsByCourseId(id);
    }

    @Override
    @Transactional
    public void save(List<Labs> labs) {
        labsDAO.save(labs);
    }

    @Override
    @Transactional
    public Labs findLabsId(int id) {
        return labsDAO.findLabsId(id);
    }
}
