package com.example.healthstore;

public class Comment {

    String username;
    String Comment;

    public Comment(String username, String comment) {
        this.username = username;
        Comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
