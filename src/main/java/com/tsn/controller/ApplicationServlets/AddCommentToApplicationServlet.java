package com.tsn.controller.ApplicationServlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsn.model.Application;
import com.tsn.service.ApplicationService;
import com.tsn.util.ApplicationIdAndCommentText;
import com.tsn.util.UserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddCommentToApplicationServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // set response type to JSON
        response.setContentType("application/json");

        BufferedReader br = null;
        ApplicationIdAndCommentText applicationIdAndCommentText = null;

        ObjectMapper mapper = new ObjectMapper();

        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = br.readLine();

            // Convert received JSON to String
            applicationIdAndCommentText = mapper.readValue(json, ApplicationIdAndCommentText.class);
        } catch(IOException e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            String exceptionString = "Error happened while trying to get comment text from JSON file!" + e;
            mapper.writeValue(response.getOutputStream(), exceptionString);
        } finally {
            if(null == applicationIdAndCommentText) {
                response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                String exceptionString = "Error happened while trying to get comment text from JSON file!" + " applicationIdAndCommentText == null";
                mapper.writeValue(response.getOutputStream(), exceptionString);
            }
        }

        ApplicationService applicationService = new ApplicationService();
        UserDetails userDetails = (UserDetails) request.getSession().getAttribute("user");
        applicationService.addCommentToApplication(applicationIdAndCommentText, userDetails);

        response.setStatus(HttpServletResponse.SC_OK);
        mapper.writeValue(response.getOutputStream(), "ok");
    }
}
