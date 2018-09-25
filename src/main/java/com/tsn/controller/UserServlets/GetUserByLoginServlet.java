package com.tsn.controller.UserServlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.tsn.model.User;
import com.tsn.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetUserByLoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");

        BufferedReader br = null;
        String userLogin = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = br.readLine();

            userLogin = mapper.readValue(json, String.class);
        } catch(IOException e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            String exceptionString = "Error happened while trying to get userLogin data from JSON file!" + e;
            mapper.writeValue(response.getOutputStream(), exceptionString);
        } finally {
            if(null == userLogin) {
                response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                String exceptionString = "Error happened while trying to get userLogin data from JSON file!" + " newApplication == null";
                mapper.writeValue(response.getOutputStream(), exceptionString);
            }
        }

        UserService userService = new UserService();
        User user = userService.getUserByLogin(userLogin);

        response.setStatus(HttpServletResponse.SC_OK);
        mapper.writeValue(response.getOutputStream(), user);
    }

}
