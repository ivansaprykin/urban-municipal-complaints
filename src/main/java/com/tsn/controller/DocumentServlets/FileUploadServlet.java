package com.tsn.controller.DocumentServlets;

import com.tsn.model.Document;
import com.tsn.service.DocumentService;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "fileUpload",urlPatterns = {"/authorized/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = getFileName(filePart);
        InputStream fileContent = filePart.getInputStream();
        // ... (do your job here)

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        for (int length = 0; (length = fileContent.read(buffer)) > 0;) {
            output.write(buffer, 0, length);
        }

        Document document = new Document();
        document.setCreationDate(LocalDate.now().toString());
        document.setName(fileName);
        document.setDocument(output.toByteArray());

        DocumentService documentService = new DocumentService();
        documentService.saveDocument(document);

    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
