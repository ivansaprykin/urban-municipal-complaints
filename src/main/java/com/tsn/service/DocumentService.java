package com.tsn.service;

import com.tsn.dao.DocumentDao;
import com.tsn.model.Document;

import java.util.List;

public class DocumentService {

    DocumentDao documentDao = new DocumentDao();

    public void saveDocument(Document document) {
        documentDao.saveDocument(document);
    }

    public void updateDocument(Document document) {
        documentDao.updateDocument(document);
    }

    public void deleteDocument(Document document) {
        documentDao.deleteDocument(document);
    }

    public Document getDocumentByName(String  documentName) {
        return documentDao.getDocumentByName(documentName);
    }

    public List<Document> getAllDocuments() {
        return documentDao.getAllDocuments();
    }
}
