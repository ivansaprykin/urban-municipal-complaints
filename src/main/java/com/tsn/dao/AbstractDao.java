package com.tsn.dao;

import com.tsn.util.MyHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AbstractDao {

    private Session getSession() {
        return MyHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public void save(Object entity) {

        Session session = getSession();
        Transaction txD;

        if (session.getTransaction() != null
                && session.getTransaction().isActive()) {
            txD = session.getTransaction();
        } else {
            txD = session.beginTransaction();
        }


        session.save(entity);
        try {
            txD.commit();
            while(!txD.wasCommitted());
        }catch (RuntimeException  e) {
            txD.rollback() ;

        } finally {
            txD = null;
        }

        /*Session session = getSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();*/
    }

    public void update(Object entity) {

        Session session = getSession();
        Transaction txD;

        if (session.getTransaction() != null
                && session.getTransaction().isActive()) {
            txD = session.getTransaction();
        } else {
            txD = session.beginTransaction();
        }


        session.saveOrUpdate(entity);
        try {
            txD.commit();
            while(!txD.wasCommitted());
        }catch (RuntimeException  e) {
            txD.rollback() ;

        } finally {
            txD = null;
        }


       /* session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();*/
    }

    public void delete(Object entity) {
        Session session = getSession();
        Transaction txD;

        if (session.getTransaction() != null
                && session.getTransaction().isActive()) {
            txD = session.getTransaction();
        } else {
            txD = session.beginTransaction();
        }


        session.delete(entity);
        try {
            txD.commit();
            while(!txD.wasCommitted());
        }catch (RuntimeException  e) {
            txD.rollback() ;

        } finally {
            txD = null;
        }



       /* Session session = getSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();*/
    }
}
