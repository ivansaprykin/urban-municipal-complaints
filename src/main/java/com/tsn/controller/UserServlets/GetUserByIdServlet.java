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

public class GetUserByIdServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //response.setContentType("application/json");

        BufferedReader br = null;
        Integer userId = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = br.readLine();

            userId = mapper.readValue(json, Integer.class);
        } catch(IOException e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            String exceptionString = "Error happened while trying to get userId data from JSON file!" + e;
            mapper.writeValue(response.getOutputStream(), exceptionString);
        } finally {
            if(null == userId) {
                response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                String exceptionString = "Error happened while trying to get userId data from JSON file!" + " userId == null";
                mapper.writeValue(response.getOutputStream(), exceptionString);
            }
        }


        UserService userService = new UserService();
        User user = userService.getUserById(userId.intValue());

        response.setStatus(HttpServletResponse.SC_OK);
        mapper.writeValue(response.getOutputStream(), user);
    }

}