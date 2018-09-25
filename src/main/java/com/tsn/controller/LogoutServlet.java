package com.tsn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LogoutServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //  Set response type to JSON
        response.setContentType("application/json");

        // initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        // delete session attribute

        HttpSession session = request.getSession();
        session.getAttribute("user");
        session.removeAttribute("user");

        String responseString = "Success";
        response.setStatus(HttpServletResponse.SC_OK);
        mapper.writeValue(response.getOutputStream(), responseString);

    }
}

