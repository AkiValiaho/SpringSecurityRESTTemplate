package com.valiaho.Service;

import com.valiaho.Domain.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Created by akivv on 7.4.2016.
 */
@Service
public interface ImageService {
    byte[] loadRandomImageAsByteArray();

    Optional<Image> uploadImage(MultipartFile file);

    Optional<Image> uploadImage(MultipartFile file, String imageName);
}
