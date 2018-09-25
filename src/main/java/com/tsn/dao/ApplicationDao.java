package com.tsn.dao;

import com.tsn.model.Application;
import com.tsn.model.User;
import com.tsn.util.MyHibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.Set;

public class ApplicationDao extends AbstractDao {

    private Session getSession() {
        return MyHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public void saveApplication(Application application) {
        save(application);
    }

    public void updateApplication(Application application) {
        update(application);
    }

    public void deleteApplication(Application application) {
        delete(application);
    }

    public Application getApplicationById(int applicationId) {
        Session session = getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Application.class);
        criteria.add(Restrictions.eq("id", applicationId));
        Application application = (Application) criteria.uniqueResult();
        session.getTransaction().commit();
        return application;
    }

    public Set<Application> getAllApplications() {
        Session session = getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Application.class);
        Set<Application> applications = new HashSet<Application>();
        applications.addAll(criteria.list());
        session.getTransaction().commit();
        return applications;
    }

    public Set<Application> getApplicationsByUser(String userLogin) {
        Session session = getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Application.class);
        criteria.add(Restrictions.eq("authorName", userLogin));
        Set<Application> applications = new HashSet<Application>();
        applications.addAll(criteria.list());
        session.getTransaction().commit();
        return applications;
    }
}
