package com.tsn.controller.DocumentServlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsn.model.Document;
import com.tsn.service.DocumentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetAllDocumentsNamesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // set response type to JSON
        response.setContentType("application/json");

        DocumentService documentService = new DocumentService();
        List<Document> allDocuments = documentService.getAllDocuments();

        List<String> documentsNames = new ArrayList<String>();
        for(Document document: allDocuments) {
            if( ! documentsNames.contains(document.getName())) {
                documentsNames.add(document.getName());
            }
        }


        //  initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        // send List<Enrollee> as JSON to client
        mapper.writeValue(response.getOutputStream(), documentsNames);
    }

}
