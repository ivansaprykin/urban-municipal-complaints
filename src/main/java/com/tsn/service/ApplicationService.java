package com.tsn.service;

import com.tsn.dao.ApplicationDao;
import com.tsn.model.Application;
import com.tsn.model.Comment;
import com.tsn.util.ApplicationIdAndCommentText;
import com.tsn.util.UserDetails;
import org.joda.time.LocalDateTime;


import java.util.Set;

public class ApplicationService {

    ApplicationDao applicationDao = new ApplicationDao();

    public void saveApplication(Application application, String userName) {
        application.setDate(LocalDateTime.now());
        application.setAuthorName(userName);
        applicationDao.saveApplication(application);
    }

    public Application getApplicationById(int id) {
        return applicationDao.getApplicationById(id);
    }

    public void addCommentToApplication(ApplicationIdAndCommentText applicationIdAndCommentText, UserDetails author) {


        int applicationId = applicationIdAndCommentText.getApplicationId();
        String comment = applicationIdAndCommentText.getCommentText();

        Application application = applicationDao.getApplicationById(applicationId);
        Comment newComment = new Comment();
        newComment.setDate(LocalDateTime.now());
        newComment.setText(comment);
        if(author.getUserRole().equals("Admin")) {
            newComment.setAuthor("Admin");
        } else {
            newComment.setAuthor(author.getUserName());
        }

        application.getComments().add(newComment);
        applicationDao.updateApplication(application);
    }

    public Set<Application> getAllApplications() {
        return applicationDao.getAllApplications();
    }

    public void deleteApplication(Application application) {
        applicationDao.deleteApplication(application);
    }

    public Set<Application> getApplicationsByUser(String userLogin) {
        return applicationDao.getApplicationsByUser(userLogin);
    }
}
