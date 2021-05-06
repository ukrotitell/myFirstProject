package com.gonchcharov.ru.EPAMFinalProject.dao;

import com.gonchcharov.ru.EPAMFinalProject.entity.Document;
import com.gonchcharov.ru.EPAMFinalProject.entity.Labs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DocumentRepository  {

    List<Document> findDocumentsOfStudent( Labs lab);

    public void save(Document document);

    Document findDocumentById( int docId);
}
