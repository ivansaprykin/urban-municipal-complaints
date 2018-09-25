package com.tsn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsn.dao.UserDao;
import com.tsn.model.User;
import com.tsn.service.UserService;
import com.tsn.util.Credentials;
import com.tsn.util.PasswordHash;
import com.tsn.util.UserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //  Set response type to JSON
        response.setContentType("application/json");

        BufferedReader br = null;
        Credentials userCredentials = null;
        // initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = br.readLine();

            // 3. Convert received JSON to String
            userCredentials = mapper.readValue(json, Credentials.class);
        } catch(IOException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            String exceptionString = "Error happened while trying to get user input from JSON file!" + e.getLocalizedMessage();
            mapper.writeValue(response.getOutputStream(), exceptionString);
        } finally {
            if(userCredentials == null) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                String exceptionString = "Error happened while trying to get user input from JSON file!" + " userCredentials == null";
                mapper.writeValue(response.getOutputStream(), exceptionString);
            }
        }

        // validate user сredentials

        // check if user with inputted e-mail exist in DB
        boolean userExist = false;
        User user = null;
        UserService userService = new UserService();
        if(userCredentials != null) {
            user = userService.getUserByLogin(userCredentials.getLogin());
            if(user != null) {
                userExist = true;
            }

        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            String exceptionString = "Error happened while trying to get user input from JSON file!" + " userCredentials == null";
            mapper.writeValue(response.getOutputStream(), exceptionString);
        }

        if( ! userExist) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            String exceptionString = "Неверный логин или пароль";
            mapper.writeValue(response.getOutputStream(), exceptionString);
        }


        // check if password is correct
        boolean passwordIsCorrect = PasswordHash.validatePassword(userCredentials.getPassword(), user.getPasswordMd5(), user.getSaltForPassword());

        if(passwordIsCorrect) {
            // add cookie
            //response.addCookie() .cookie("userEmailSurveyApp", validatedUserEmail, 60*60*24*30, true);

            // set session attribute

            UserDetails userDetails = new UserDetails(user.getId(), user.getLogin(), user.getRole().getName(), user.getLastName() + " " + user.getFirstName());
            HttpSession session = request.getSession();
            session.setAttribute("user", userDetails);

            String responseString = userDetails.getUserRole();
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), responseString);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
            String exceptionString = "Неверный логин или пароль";
            mapper.writeValue(response.getOutputStream(), exceptionString);
        }
    }
}
