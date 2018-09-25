package com.tsn.controller.NewsServlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.tsn.dao.ArticleDao;
import com.tsn.model.Article;
import com.tsn.service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class GetAllNewsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // set response type to JSON
        response.setContentType("application/json");

        NewsService newsService = new NewsService();
        List<Article> allArticles = newsService.getAllArticles();

        //  initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());

        // send List<Enrollee> as JSON to client
        mapper.writeValue(response.getOutputStream(), allArticles);
    }
}
