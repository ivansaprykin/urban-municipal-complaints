package com.tsn.dao;

import com.tsn.model.Document;
import com.tsn.util.MyHibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DocumentDao extends AbstractDao {

    private Session getSession() {
        return MyHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public void saveDocument(Document document) {
        save(document);
    }

    public void updateDocument(Document document) {
        update(document);
    }

    public void deleteDocument(Document document) {
        delete(document);
    }

    public Document getDocumentByName(String documentName) {
        Session session = getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Document.class);
        criteria.add(Restrictions.eq("name", documentName));
        Document document = (Document) criteria.uniqueResult();
        session.getTransaction().commit();
        return document;
    }

    public List<Document> getAllDocuments() {
        Session session = getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Document.class);
        List<Document> documents = (List<Document>) criteria.list();
        session.getTransaction().commit();
        return documents;
    }
}
