package com.tsn.controller.DocumentServlets;

import com.tsn.model.Document;
import com.tsn.service.DocumentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class DeleteDocumentByNameServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String documentName =  request.getParameter("documentName");


        DocumentService documentService = new DocumentService();
        List<Document> documentList = documentService.getAllDocuments();
        for(Document document: documentList) {
            if(document.getName().equals(documentName)) {
                documentService.deleteDocument(document);
            }
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
