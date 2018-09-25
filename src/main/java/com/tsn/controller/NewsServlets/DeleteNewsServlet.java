package com.tsn.controller.NewsServlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.tsn.model.Article;
import com.tsn.service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteNewsServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");

        BufferedReader br = null;
        Article article = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = br.readLine();

            article = mapper.readValue(json, Article.class);
        } catch(IOException e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            String exceptionString = "Error happened while trying to get article data from JSON file!" + e;
            mapper.writeValue(response.getOutputStream(), exceptionString);
        } finally {
            if(null == article) {
                response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                String exceptionString = "Error happened while trying to get article data from JSON file!" + " newApplication == null";
                mapper.writeValue(response.getOutputStream(), exceptionString);
            }
        }

        NewsService newsService = new NewsService();
        newsService.deleteNews(article);

        response.setStatus(HttpServletResponse.SC_OK);
        mapper.writeValue(response.getOutputStream(), "ok");
    }
}
