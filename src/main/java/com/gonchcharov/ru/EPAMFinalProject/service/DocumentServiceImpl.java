package com.gonchcharov.ru.EPAMFinalProject.service;

import com.gonchcharov.ru.EPAMFinalProject.dao.DocumentRepository;
import com.gonchcharov.ru.EPAMFinalProject.entity.Document;
import com.gonchcharov.ru.EPAMFinalProject.entity.Labs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DocumentServiceImpl implements DocumentService{
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    @Transactional
    public List<Document> findDocumentsOfStudent(Labs lab) {

        return documentRepository.findDocumentsOfStudent(lab);
    }

    @Override
    @Transactional
    public void save(Document document) {
        documentRepository.save(document);
    }

    @Override
    @Transactional
    public Document findDocumentById(int docId) {
        return documentRepository.findDocumentById(docId);
    }
}
