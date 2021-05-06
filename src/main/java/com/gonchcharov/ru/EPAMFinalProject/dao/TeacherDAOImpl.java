package com.gonchcharov.ru.EPAMFinalProject.dao;

import com.gonchcharov.ru.EPAMFinalProject.entity.Student;
import com.gonchcharov.ru.EPAMFinalProject.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
@Repository
public class TeacherDAOImpl implements TeacherDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public Teacher findTeacherById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using name
        Query<Teacher> theQuery = currentSession.createQuery(" from Teacher  where user.id=:userId ", Teacher.class);
        theQuery.setParameter("userId", id);

        Teacher teacher = null;

        try {
            teacher = theQuery.getSingleResult();
        } catch (Exception e) {
            teacher = null;
        }
        return teacher;

    }

    @Override
    public void save(Teacher teacher) {
        Session currentSession = entityManager.unwrap(Session.class);

        // create the user ... finally LOL
        currentSession.saveOrUpdate(teacher);
    }
}
