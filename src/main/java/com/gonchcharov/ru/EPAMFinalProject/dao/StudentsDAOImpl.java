package com.gonchcharov.ru.EPAMFinalProject.dao;

import com.gonchcharov.ru.EPAMFinalProject.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class StudentsDAOImpl implements StudentsDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Student findStudentById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Student> theQuery = currentSession.createQuery(" from Student  where user.id=:userId ", Student.class);
        theQuery.setParameter("userId", id);

        Student student = null;

        try {
            student = theQuery.getSingleResult();
        } catch (Exception e) {
            student = null;
        }
        return student;

    }



    @Override
    public void save(Student student) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(student);
    }

    @Override
    public void save(Document document) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(document);
    }




}
