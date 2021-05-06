package com.gonchcharov.ru.EPAMFinalProject.dao;

import com.gonchcharov.ru.EPAMFinalProject.entity.Courses;
import com.gonchcharov.ru.EPAMFinalProject.entity.Labs;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LabsDAOImpl implements LabsDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Labs> findLabsByCourseId(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using name
        Query<Labs> theQuery = currentSession.createQuery(" from Labs where courseId=:labsId ", Labs.class);
        theQuery.setParameter("labsId", id);

        List<Labs> labs = null;

        try {
            labs = theQuery.getResultList();
        } catch (Exception e) {
            labs = null;
        }
        return labs;
    }

    @Override
    public void save(List<Labs> labs) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(labs);
    }

    @Override
    public Labs findLabsId(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using name
        Query<Labs> theQuery = currentSession.createQuery(" from Labs   where id=:labId ", Labs.class);
        theQuery.setParameter("labId", id);

        Labs lab = null;

        try {
            lab = theQuery.getSingleResult();
        } catch (Exception e) {
            lab = null;
        }
        return lab;
    }
}



