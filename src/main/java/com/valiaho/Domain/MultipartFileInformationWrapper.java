package com.valiaho.Domain;

import java.util.List;

/**
 * Created by akivv on 1.5.2016.
 */
public class MultipartFileInformationWrapper {
    //TODO Refactor multipatfile out of multipartfileWrapper according to: http://stackoverflow.com/questions/23533355/spring-controller-requestbody-with-file-upload-is-it-possible
    private List<Comment> commentList;
    private String originalFilename;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }
}
