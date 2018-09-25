package com.tsn.dao;

import com.tsn.model.Article;
import com.tsn.util.MyHibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ArticleDao extends AbstractDao {

    private Session getSession() {
        return MyHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public void saveArticle(Article article) {
        save(article);
    }

    public void updateArticle(Article article) {
        update(article);
    }

    public void deleteArticle(Article article) {
        delete(article);
    }

    public Article getArticleById(int enrolleeId) {
        Session session = getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Article.class);
        criteria.add(Restrictions.eq("id", enrolleeId));
        Article article = (Article) criteria.uniqueResult();
        session.getTransaction().commit();
        return article;
    }

    public List<Article> getAllArticles() {
        Session session = getSession();
        try {
            session.getTransaction().begin();
            List<Article> articles = session.createCriteria(Article.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            session.getTransaction().commit();
            return articles;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

}
