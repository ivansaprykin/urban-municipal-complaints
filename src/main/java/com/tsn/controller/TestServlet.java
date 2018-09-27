package com.tsn.controller;

import com.tsn.dao.RoleDaoImpl;
import com.tsn.dao.UserDao;
import com.tsn.model.*;
import com.tsn.service.ApplicationService;
import com.tsn.service.DocumentService;
import com.tsn.service.NewsService;
import com.tsn.service.UserService;
import com.tsn.util.PasswordHash;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.*;

public class TestServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/txt");
        //PrintWriter out = response.getWriter();


        ApplicationService applicationService = new ApplicationService();
        Set<Application> applications = applicationService.getApplicationsByUser("Admin");
        for (Application application : applications) {
            applicationService.deleteApplication(application);
        }

        DocumentService documentService = new DocumentService();
        List<Document> documents = documentService.getAllDocuments();
        for (Document document : documents) {
            if (document.getName().equals("отчет3.jpg")) {
                documentService.deleteDocument(document);
            }
        }
        Document document = documents.get(0);

        OutputStream output = response.getOutputStream();
        output.write(document.getDocument());

        output.close();
        /*out.println("Dociments:");
        for(Document document : documents) {
            out.println(document);
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
        }
*/
        RoleDaoImpl roleDao = new RoleDaoImpl();
        List<Role> roles = roleDao.getAllRoles();
        if (roles.isEmpty()) {
            Role role1 = new Role();
            role1.setName("Admin");

            Role role2 = new Role();
            role2.setName("User");

            roleDao.saveRole(role1);
            roleDao.saveRole(role2);

            roles = roleDao.getAllRoles();
        }

        UserDao userDao = new UserDao();
        List<User> users = userDao.getAllUsers();
        if (users.isEmpty()) {
            //out.print("users.isEmpty");
            //out.println("<br>");

            User user1 = new User();
            user1.setLogin("login1");
            PasswordHash.createNewSalt();
            user1.setPasswordMd5(PasswordHash.createHash("password1"));
            user1.setSaltForPassword(PasswordHash.currentSalt);
            user1.setRole(roles.get(0));
            user1.setFirstName("Admin");


            User user2 = new User();
            user2.setLogin("login2");
            PasswordHash.createNewSalt();
            user2.setSaltForPassword(PasswordHash.currentSalt);
            user2.setPasswordMd5(PasswordHash.createHash("password2"));
            user2.setRole(roles.get(1));
            user2.setFirstName("Иван");
            user2.setBuildingAddress("ул. Вакуленчука 26");
            user2.setApartmentNumber("10");
            user2.setMiddleName("Иванович");
            user2.setLastName("Иванов");

            userDao.saveUser(user1);
            userDao.saveUser(user2);

            users = userDao.getAllUsers();
        }


        /*out.println("users.size = " + users.size());
        out.println("<br>");
        out.println("<br>");
        out.println("<br>");

        for(User user : users) {
            out.println(user);
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");*/


        Article article = new Article();
        article.setDate(LocalDate.now());
        article.setText("Скоро в нашем доме будут покрашены все подъезды в светло зеленый цвет. Просим Вас обратить на это внимание и не запачькать одежду и обубь. Так же Хотим обраить Ваше снимание на то, что при покраске будет использоваться специальная водостойкая краска без запаха.");
        article.setTitle("В нашем доме будут покрашены все подъезды!");


        NewsService newsService = new NewsService();
        newsService.saveNews(article);

        List<Article> articles = newsService.getAllArticles();

        /*for(Article article1 : articles) {
            out.println(article1);
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
        }*/


        UserService userService = new UserService();
        User user2 = userService.getUserByLogin("login2");

        Application application = new Application();
        application.setText("Прошу починить крышку люка на крышу в подъезде 2. Во время дождя через крышку просачивается вода.");
        application.setDate(LocalDateTime.now());

        Comment comment = new Comment();
        comment.setAuthor(users.get(0).getFirstName());
        comment.setDate(LocalDateTime.now());
        comment.setText("Все исправим. Чесно! И проверим. ");

        Set<Comment> comments = new HashSet<Comment>();
        comments.add(comment);

        application.setComments(comments);

        List<Application> applicationList = new ArrayList<Application>();
        applicationList.add(application);

        user2.setApplications(applicationList);
        userService.updateUser(user2);


        applicationService.saveApplication(application, user2.getLogin());


        /*Set<Application> applications = applicationService.getAllApplications();*/
    }

}


