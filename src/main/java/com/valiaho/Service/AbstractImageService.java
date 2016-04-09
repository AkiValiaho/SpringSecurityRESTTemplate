package com.valiaho.Service;

import com.valiaho.DAO.ImageRepository;
import com.valiaho.Domain.Builders.PersistImageObjectBuilder;
import com.valiaho.Domain.Image;
import com.valiaho.Utils.MultiPartFileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by akivv on 7.4.2016.
 */
@Service
public abstract class AbstractImageService implements ImageService {
    @Autowired
    MultiPartFileUtils multiPartFileUtils;
    @Autowired
    ImageRepository imageRepository;
    private Logger logger = Logger.getLogger(AbstractImageService.class);

    @Override
    public Optional<Image> uploadImage(MultipartFile file, String imageName) throws RuntimeException {
        try {
            return pushImageToCouchbase(file, imageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * @param file {@MultipartFile} to persist
     * @return {@Optional} returns an optional-type Image
     * @throws RuntimeException if image saving fails for any reasons throws a {@java.lang.RuntimeException}
     */
    @Override
    public Optional<Image> uploadImage(MultipartFile file) throws RuntimeException {
        return pushImageToCouchbase(file, "");
    }

    private Optional<Image> pushImageToCouchbase(MultipartFile file, String imageName) {
        try {
            if (imageName.isEmpty()) {
                imageName = multiPartFileUtils.saveMultiPartFileToPersistence(file);
            } else {
                multiPartFileUtils.saveMultiPartFileToPersistence(file, imageName);
            }
            final Image build = getImageToPersist(imageName);
            final Image save = imageRepository.save(build);
            if (save == null) {
                this.logger.error("Failed to save the image with image name " + imageName);
                throw new RuntimeException("Failed to save the image with image name " + imageName);
            }
            return Optional.of(save);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Image getImageToPersist(String imageName) throws IllegalArgumentException {
        if (imageName == null || imageName.isEmpty()) {
            throw new IllegalArgumentException("Argument name is not valid");
        }
        final PersistImageObjectBuilder persistImageObjectBuilder = ImageBuilderFactory.buildPersistImageObjectBuilder();
        persistImageObjectBuilder.setImageName(imageName);
        return persistImageObjectBuilder.build();
    }

    @Override
    public byte[] loadRandomImageAsByteArray() {
        //TODO Load Random image info from database and serve it from classpath
        //Turn fetched observable into a byte array
        return null;
    }
}
