package com.valiaho.Domain;

/**
 * Created by akivv on 19.4.2016.
 */
public class Comment {
    private String comment = "";

    public Comment() {
    }

    public Comment(String commentRepresentation) {
        this.comment = commentRepresentation;
    }

    public String getComment() {
        return comment;
    }
}
