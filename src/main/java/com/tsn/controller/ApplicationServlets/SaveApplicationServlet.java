package com.tsn.controller.ApplicationServlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.tsn.model.Application;
import com.tsn.service.ApplicationService;
import com.tsn.util.UserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SaveApplicationServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // set response type to JSON
        response.setContentType("application/json");

        BufferedReader br = null;
        Application application = null;
        // initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = br.readLine();

            // 3. Convert received JSON to String
            application = mapper.readValue(json, Application.class);
        } catch(IOException e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            String exceptionString = "Error happened while trying to get application data from JSON file!" + e;
            mapper.writeValue(response.getOutputStream(), exceptionString);
        } finally {
            if(null == application) {
                response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                String exceptionString = "Error happened while trying to get application data from JSON file!" + " newApplication == null";
                mapper.writeValue(response.getOutputStream(), exceptionString);
            }
        }

        ApplicationService applicationService = new ApplicationService();
        UserDetails userDetails = (UserDetails) request.getSession().getAttribute("user");
        String userName = userDetails.getUserLogin();
        applicationService.saveApplication(application, userName);

        response.setStatus(HttpServletResponse.SC_OK);
        mapper.writeValue(response.getOutputStream(), "ok");
    }
}
