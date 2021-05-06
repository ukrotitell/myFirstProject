package com.gonchcharov.ru.EPAMFinalProject.dao;

import com.gonchcharov.ru.EPAMFinalProject.entity.Courses;
import com.gonchcharov.ru.EPAMFinalProject.entity.Document;
import com.gonchcharov.ru.EPAMFinalProject.entity.Labs;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Document> findDocumentsOfStudent(Labs lab) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Document> theQuery = currentSession.createQuery(" select new Document (d.id, d.name, d.size, d.student)" +
                "from Document d  where  d.lab =: labId  ", Document.class);
        theQuery.setParameter("labId", lab);
        List<Document> documents = null;
        try {
            documents = theQuery.getResultList();
        } catch (Exception e) {
            documents = null;
        }
        return documents;
    }

    @Override
    public void save(Document document) {
            Session currentSession = entityManager.unwrap(Session.class);
            currentSession.saveOrUpdate(document);
    }

    @Override
    public Document findDocumentById(int docId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Document> theQuery = currentSession.createQuery("from Document d  where  d.id =: docId  ", Document.class);
        theQuery.setParameter("docId", docId);
        Document document = null;
        try {
            document = theQuery.getSingleResult();
        } catch (Exception e) {
            document = null;
        }
        return document;
    }
}
