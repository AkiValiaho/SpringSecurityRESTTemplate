package com.valiaho.Utils;

import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
@PropertySource("classpath:resourceProperties/imageFileProperty.properties")
public class MultiPartFileUtils {
    Logger logger = LoggerFactory.getLogger(MultiPartFileUtils.class);
    @Autowired
    Environment environment;

    public MultiPartFileUtils() {
    }

    public String saveMultiPartFileToPersistence(MultipartFile file, String imageName) throws IOException, IllegalArgumentException {
        //Generate random name for file
        if (environment == null) {
            logger.error("Environment cannot be null", IllegalArgumentException.class);
            throw new IllegalArgumentException("Environment cannot be null");
        }
        String fileName = buildFileNameWithFileType(file, imageName);
//        String fileName = String.valueOf(new LocalTime().toString());
        String imageFolderLocationFromConfig = null;
        try {
            imageFolderLocationFromConfig = getImageFolderLocationFromConfig();
        } catch (IllegalStateException e) {
            logger.error("Failed to load info about images-folder from environment.", IllegalStateException.class);
        }
        writeToSysFile(file, fileName, imageFolderLocationFromConfig);
        return fileName;
    }

    private String buildFileNameWithFileType(MultipartFile file, String imageName) {
        if (imageName.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            String str = LocalTime.now().toString();
            str = str.replace(':', 'a');
            builder.append(str);
            builder.append("." + parseEndingFromContentType(file.getContentType()));
            return builder.toString();
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(imageName);
            builder.append(parseEndingFromContentType(file.getContentType()));
            return builder.toString();
        }
    }

    private String parseEndingFromContentType(String contentType) {
        return contentType.split("/")[1];
    }

    private void writeToSysFile(MultipartFile file, String fileName, String imageFolderLocationFromConfig) {
        try {
            file.transferTo(new File(imageFolderLocationFromConfig + "/" + fileName));
        } catch (IOException e) {
            logger.error("IOException happened with image string " + imageFolderLocationFromConfig + "/" + fileName, IOException.class);
            e.printStackTrace();
        }
    }

    public String saveMultiPartFileToPersistence(MultipartFile file) throws IOException, IllegalArgumentException {
        //Generate random name for file
        if (environment == null) {
            logger.error("Environment cannot be null", IllegalArgumentException.class);
            throw new IllegalArgumentException("Environment cannot be null");
        }

        String fileName = buildFileNameWithFileType(file, "");
        String imageFolderLocationFromConfig = null;
        try {
            imageFolderLocationFromConfig = getImageFolderLocationFromConfig();
        } catch (IllegalStateException e) {
            logger.error("Failed to load info about images-folder from environment.", IllegalStateException.class);
        }
        writeToSysFile(file, fileName, imageFolderLocationFromConfig);
        return fileName;
    }

    public String getImageFolderLocationFromConfig() throws IllegalStateException {
        //TODO: Get image folder location from classpath resource configuration
        if (this.environment == null) {
            throw new IllegalStateException("State of the environment cannot be null");
        } else {
            return environment.getRequiredProperty("imageFileFolderLocation");
        }


    }
}