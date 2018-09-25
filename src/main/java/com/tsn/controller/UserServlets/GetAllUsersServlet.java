package com.tsn.controller.UserServlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.tsn.model.User;
import com.tsn.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllUsersServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");

        UserService userService = new UserService();
        List<User> users = userService.getAllUsers();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());

        mapper.writeValue(response.getOutputStream(), users);
    }

}
