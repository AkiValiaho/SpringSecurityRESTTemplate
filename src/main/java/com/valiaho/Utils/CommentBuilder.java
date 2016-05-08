package com.valiaho.Utils;

import com.valiaho.Domain.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akivv on 20.4.2016.
 */
public class CommentBuilder {
    private String[] array;

    public CommentBuilder(String... stringArray) {
        this.array = stringArray;
    }

    public List<Comment> build() {
        List<Comment> commentList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            commentList.add(new Comment(array[i]));
        }
        return commentList;
    }

    /**
     * @param i
     * @return
     */
    public CommentBuilder generateRandomComments(int i) {
        List<String> randomComments = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            randomComments.add(String.valueOf(j));
        }
        array = randomComments.toArray(new String[randomComments.size()]);
        return this;
    }
}
