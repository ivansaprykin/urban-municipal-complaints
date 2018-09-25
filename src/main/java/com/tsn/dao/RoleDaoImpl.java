package com.tsn.dao;

import com.tsn.model.Role;
import com.tsn.util.MyHibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class RoleDaoImpl extends AbstractDao {

    private Session getSession() {
        return MyHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public void saveRole(Role role) {
        save(role);
    }

    public void updateRole(Role role) {
        update(role);
    }

    public void deleteRole(Role role) {
        delete(role);
    }

    public Role getRoleById(int roleId) {
        Session session = getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("id", roleId));
        Role enrollee = (Role) criteria.uniqueResult();
        session.getTransaction().commit();
        return enrollee;
    }

    public List<Role> getAllRoles() {
        Session session = getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Role.class);
        List<Role> specializations = (List<Role>) criteria.list();
        session.getTransaction().commit();
        return specializations;
    }
}
