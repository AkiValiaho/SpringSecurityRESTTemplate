package com.valiaho.Tests.Controllers.Domain;

import com.valiaho.Domain.Comment;
import com.valiaho.Domain.MultipartFileInformationWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by akivv on 7.5.2016.
 */
public class MultipartFileWrapperBuilder {
    private List<Comment> commentLIst;
    private String originalFileName;
    private MultipartFile multiPartFile;

    public MultipartFileWrapperBuilder() {
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public List<Comment> getCommentLIst() {
        return commentLIst;
    }

    public void setCommentLIst(List<Comment> commentLIst) {
        this.commentLIst = commentLIst;
    }

    public MultipartFileInformationWrapper build() {
        final MultipartFileInformationWrapper multipartFileInformationWrapper = new MultipartFileInformationWrapper();
        setInfoFromBuilderToNewWrapper(multipartFileInformationWrapper);
        return multipartFileInformationWrapper;
    }

    private void setInfoFromBuilderToNewWrapper(MultipartFileInformationWrapper multipartFileInformationWrapper) {
        multipartFileInformationWrapper.setCommentList(this.commentLIst);
        multipartFileInformationWrapper.setOriginalFilename(this.originalFileName);
    }
}
