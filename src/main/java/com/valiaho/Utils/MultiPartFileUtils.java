package com.valiaho.Utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

    public String[] saveMultiPartFileToPersistence(MultipartFile file, @Nullable String imageName) throws IOException, IllegalArgumentException {
        //Generate random name for file
        if (environment == null) {
            logger.error("Environment cannot be null", IllegalArgumentException.class);
            throw new IllegalArgumentException("Environment cannot be null");
        }
        String fileName = buildFileNameWithNullCheck(file, imageName);
//        String fileName = String.valueOf(new LocalTime().toString());
        String imageFolderLocationFromConfig = null;
        try {
            imageFolderLocationFromConfig = getImageFolderLocationFromConfig();
        } catch (IllegalStateException e) {
            logger.error("Failed to load info about images-folder from environment.", IllegalStateException.class);
        }
        writeToSysFile(file, fileName, imageFolderLocationFromConfig);
        return parseImageFolderLocationFromConfig(fileName, imageFolderLocationFromConfig);
    }

    @NotNull
    private String[] parseImageFolderLocationFromConfig(String fileName, String imageFolderLocationFromConfig) {
        String[] arrayOfImageFolderLocationAndFileName = new String[2];
        arrayOfImageFolderLocationAndFileName[0] = imageFolderLocationFromConfig;
        arrayOfImageFolderLocationAndFileName[1] = fileName;
        return arrayOfImageFolderLocationAndFileName;
    }

    @NotNull
    private String buildFileNameWithNullCheck(MultipartFile file, @Nullable String imageName) {
        String fileName;
        if (imageName == null) {
            fileName = buildFileNameWithFileType(file, "");
        } else {
            fileName = buildFileNameWithFileType(file, imageName);
        }
        return fileName;
    }

    private String buildFileNameWithFileType(MultipartFile file, String imageName) {
        if (imageName.isEmpty()) {
            return createFilenameFromLocalTime(file);
        } else {
            return createFilenameFromGivenImageNameAndContentType(file, imageName);
        }
    }

    @NotNull
    private String createFilenameFromGivenImageNameAndContentType(MultipartFile file, String imageName) {
        StringBuilder builder = new StringBuilder();
        builder.append(imageName);
        final String contentTypeEnding = parseEndingFromContentType(file.getContentType());
        if (imageName.contains(contentTypeEnding)) {
            return builder.toString();
        } else {
            builder.append(parseEndingFromContentType(file.getContentType()));
            return builder.toString();
        }
    }

    @NotNull
    private String createFilenameFromLocalTime(MultipartFile file) {
        StringBuilder builder = new StringBuilder();
        String str = LocalTime.now().toString();
        str = str.replace(':', 'a');
        builder.append(str);
        builder.append("." + parseEndingFromContentType(file.getContentType()));
        return builder.toString();
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

    public String getImageFolderLocationFromConfig() throws IllegalStateException {
        if (this.environment == null) {
            throw new IllegalStateException("State of the environment cannot be null");
        } else {
            return environment.getRequiredProperty("imageFileFolderLocation");
        }


    }
}