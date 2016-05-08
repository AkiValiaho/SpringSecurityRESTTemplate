package com.valiaho.Service;

import com.valiaho.Domain.Comment;
import com.valiaho.Domain.Image;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * Created by akivv on 7.4.2016.
 */
public interface ImageService {
    Optional<Image> persistImage(MultipartFile file, String imageName, @Nullable List<Comment> comments);

    Optional<Image> getImageById(String documentId);


}
