package com.tsn.service;

import com.tsn.dao.ArticleDao;

import com.tsn.model.Article;
import org.joda.time.LocalDate;

import java.util.List;


public class NewsService {

    ArticleDao articleDao = new ArticleDao();

    public void saveNews(Article article) {

        article.setDate(LocalDate.now());
        articleDao.saveArticle(article);
    }

    public void deleteNews(Article article) {
        articleDao.deleteArticle(article);
    }

    public List<Article> getAllArticles() {
        return articleDao.getAllArticles();
    }

    public void updateNews(Article article) {
        articleDao.updateArticle(article);
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }
}