package com.valiaho.Tests;

import com.valiaho.Domain.Image;
import com.valiaho.Service.ImageService;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.Assert.fail;

/**
 * Created by akivv on 8.4.2016.
 */
@ContextConfiguration(classes = {MyTestContext.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractImageServiceTest {

    @Autowired
    ImageService imageService;
    private MultipartFile multipartFile;

    @Before
    public void initializeBefore() throws IOException {
        final File file = FileUtils.toFile(ResourceUtils.getURL("test.png"));
        InputStream inputStream = FileUtils.openInputStream(file);
        this.multipartFile = new MockMultipartFile("Image", inputStream);
    }

    @Test
    public void uploadImage() throws Exception {
        final Optional<Image> image = imageService.uploadImage(this.multipartFile);
        if (!image.isPresent()) {
            fail("Image is not returned from the service!");
        }
    }

    @Test
    public void uploadImage1() throws Exception {

    }

    @Test
    public void loadRandomImageAsByteArray() throws Exception {

    }

    @Test
    public void getImageFolderLocationFromConfig() throws Exception {

    }
}