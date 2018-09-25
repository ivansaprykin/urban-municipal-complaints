package com.tsn.controller.DocumentServlets;

import com.tsn.model.Application;
import com.tsn.model.Document;
import com.tsn.service.ApplicationService;
import com.tsn.service.DocumentService;
import com.tsn.util.MimeTypeHelper;



import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


public class GetDocumentByNameServlet  extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String documentName =  request.getParameter("documentName");

        DocumentService documentService = new DocumentService();
        Document document = null;
        List<Document> documentList = documentService.getAllDocuments();
        for(Document document1: documentList) {
            if( document1.getName().equals(documentName)) {
                document = document1;
            }
        }


       /* MagicMatch match = null;
        try {
            match = Magic.getMagicMatch(document.getDocument());
        } catch(Exception e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            PrintWriter out = response.getWriter();
            out.println("Error happened while trying to get document's mime type! " + e);
        }
        String mimeType = match.getMimeType();*/

        String [] splitedFileName = document.getName().split("\\.");/////////////////////////////////////////////////////
        String fileType = splitedFileName[splitedFileName.length - 1];
        MimeTypeHelper mimeTypeHelper = new MimeTypeHelper();

        OutputStream output = response.getOutputStream();
        try {
            if(document != null) {
                output.write(document.getDocument());
            } else {
                response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                PrintWriter out = response.getWriter();
                out.println("Error happened while trying to get document content from DB!");
            }
            output.close();
        } catch(IOException e) {
            PrintWriter out = response.getWriter();
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            out.println(e);
        }

        response.setContentType(mimeTypeHelper.getMimiTypeByFileType(fileType));
        response.setContentLength(document.getDocument().length);
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
