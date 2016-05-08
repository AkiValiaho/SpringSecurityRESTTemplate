package com.valiaho.Domain;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akivv on 8.4.2016.
 */
@Document
public class Image {
    @Id
    @NotNull
    private String documentId;
    @NotNull
    @Field
    private String imageLocation;
    @Field
    private List<Comment> commentList = new ArrayList<>();

    @NotNull

    public Image(String documentId, String imageLocation) {
        this.documentId = documentId;
        this.imageLocation = imageLocation;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
