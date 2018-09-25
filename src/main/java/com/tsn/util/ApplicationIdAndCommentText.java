package com.tsn.util;

public class ApplicationIdAndCommentText {

    private int applicationId;
    private String commentText;

    public ApplicationIdAndCommentText() {
    }

    public ApplicationIdAndCommentText(int applicationId, String commentText) {
        this.applicationId = applicationId;
        this.commentText = commentText;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "ApplicationIdAndCommentText{" +
                "applicationId=" + applicationId +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}
