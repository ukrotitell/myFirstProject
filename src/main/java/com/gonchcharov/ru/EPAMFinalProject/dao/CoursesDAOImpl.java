package com.gonchcharov.ru.EPAMFinalProject.dao;

import com.gonchcharov.ru.EPAMFinalProject.entity.Courses;
import com.gonchcharov.ru.EPAMFinalProject.entity.Labs;
import com.gonchcharov.ru.EPAMFinalProject.entity.Student;
import com.gonchcharov.ru.EPAMFinalProject.service.UserService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

@Repository
public class CoursesDAOImpl implements CoursesDAO {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserService userService;

    @Override
    public List<Courses> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using name
        Query<Courses> theQuery = currentSession.createQuery("from Courses ", Courses.class);
        List results = theQuery.list();
        return results;
    }

    @Override
    public Courses findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using name
        Query<Courses> theQuery = currentSession.createQuery(" from Courses   where id=:courseId ", Courses.class);
        theQuery.setParameter("courseId", id);

        Courses course = null;

        try {
            course = theQuery.getSingleResult();
        } catch (Exception e) {
            course = null;
        }
        return course;
    }

    @Override
    public void save(Courses course) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(course);
    }


}
