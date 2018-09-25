package com.tsn.controller.ApplicationServlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.tsn.model.Application;
import com.tsn.model.User;
import com.tsn.service.ApplicationService;
import com.tsn.service.UserService;
import com.tsn.util.UserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class GetAllUserApplicationsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");

        ApplicationService applicationService = new ApplicationService();


        UserDetails userDetails = (UserDetails) request.getSession().getAttribute("user");
        Set<Application> applications = applicationService.getApplicationsByUser(userDetails.getUserLogin());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());

        mapper.writeValue(response.getOutputStream(), applications);
    }
}
