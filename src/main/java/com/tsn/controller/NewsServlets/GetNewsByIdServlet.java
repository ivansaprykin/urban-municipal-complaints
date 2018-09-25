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

public class GetNewsByIdServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");

        BufferedReader br = null;
        Integer articleId = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = br.readLine();

            articleId = mapper.readValue(json, Integer.class);
        } catch(IOException e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            String exceptionString = "Error happened while trying to get articleId from JSON file!" + e;
            mapper.writeValue(response.getOutputStream(), exceptionString);
        } finally {
            if(null == articleId) {
                response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                String exceptionString = "Error happened while trying to get articleId from JSON file!" + " articleId == null";
                mapper.writeValue(response.getOutputStream(), exceptionString);
            }
        }

        NewsService newsService = new NewsService();
        Article article = newsService.getArticleById(articleId.intValue());

        response.setStatus(HttpServletResponse.SC_OK);
        mapper.writeValue(response.getOutputStream(), article);
    }

}
