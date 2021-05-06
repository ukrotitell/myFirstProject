package com.gonchcharov.ru.EPAMFinalProject.service;

import com.gonchcharov.ru.EPAMFinalProject.entity.Document;
import com.gonchcharov.ru.EPAMFinalProject.entity.Labs;

import java.util.List;

public interface DocumentService {

    public List<Document> findDocumentsOfStudent(Labs lab);

    public void save(Document document);

    Document findDocumentById( int docId);
}
