package com.valiaho.Tests;

import Utils.TestingResourceGenerator;
import com.valiaho.Domain.Builders.PersistImageObjectBuilder;
import com.valiaho.Domain.Comment;
import com.valiaho.Domain.Image;
import com.valiaho.Service.ImageBuilderFactory;
import com.valiaho.Service.ImageService;
import com.valiaho.Tests.ContextConfiguration.CouchbaseTestContext;
import com.valiaho.Tests.ContextConfiguration.GenericTestContext;
import com.valiaho.Utils.CommentBuilder;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.fail;

/**
 * Created by akivv on 8.4.2016.
 */
@ContextConfiguration(classes = {GenericTestContext.class, CouchbaseTestContext.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractImageServiceTest {
    @Autowired
    ImageService imageService;
    private Logger logger = Logger.getLogger(this.getClass());
    private MultipartFile multipartFile;

    @Before
    public void initializeBefore() throws IOException {
        this.multipartFile = TestingResourceGenerator.generateTestingMultiPartMockFile();
    }

    @Test
    public void uploadImage() throws Exception {
        final Optional<Image> image = imageService.persistImage(multipartFile, "", null);
        if (!image.isPresent()) {
            fail("Image is not returned from the service!");
        } else {
            if (!imageService.getImageById(image.get().getDocumentId()).isPresent()) {
                fail("Image was not in Database");
            }
        }
    }

    @Test
    public void uploadImageAlreadyPresentInDatabase() throws Exception {
        final Optional<Image> image = imageService.persistImage(this.multipartFile, "", null);
        final Image build;
        if (image.isPresent() && imageService.getImageById(image.get().getDocumentId()).isPresent()) {
            /* Image was properly added to DB */
            build = cloneAddedImage(image);
            addSomeNewCommentsToList(build);
            upsertNewImageIntoDatabase(build);
        } else {

            fail("Image was not present after upsertion");
        }
    }

    private void upsertNewImageIntoDatabase(Image build) {
        /* Upsert the new image */
        final Optional<Image> imageWithAddedCommentsOptional = imageService.persistImage(this.multipartFile,
                build.getDocumentId(), build.getCommentList());
        if (imageWithAddedCommentsOptional.isPresent()) {
            final Image imageWithAddedComments = imageWithAddedCommentsOptional.get();
            checkIfArrayListSizeIsEqualToNewObject(imageWithAddedComments);
        } else {
            fail("Image was not present after upsertion");
        }
    }

    private void checkIfArrayListSizeIsEqualToNewObject(Image imageWithAddedComments) {
        final Optional<Image> imageFromPersistence = imageService.getImageById(imageWithAddedComments.getDocumentId());
        if (imageFromPersistence.isPresent()) {
            final Image image1 = imageFromPersistence.get();
            if (image1.getCommentList().size() != imageWithAddedComments.getCommentList().size()) {
                fail("method does not properly update comments to database");
            }
        } else {
            fail("Image was not found from Persistence");
        }
    }

    private void addSomeNewCommentsToList(Image imageToAddCommentsTo) {
		/* Add some comments */
        List<Comment> newComments = new CommentBuilder().generateRandomComments(5).build();
        imageToAddCommentsTo.getCommentList().addAll(newComments);
    }

    private Image cloneAddedImage(Optional<Image> image) {
        Image build;/* Clone the added Image */
        final Image imageToCopy = image.get();
        final PersistImageObjectBuilder persistImageObjectBuilder = ImageBuilderFactory
                .buildPersistImageObjectBuilder();
        build = persistImageObjectBuilder.clonePersistedImage(imageToCopy).build();
        return build;
    }

    private Optional<Image> uploadImageToPersistence(@Nullable List<Comment> commentListParameter) {
        final List<Comment> commentList;
        if (commentListParameter == null) {
            commentList = new CommentBuilder("Hello world", "What is this").build();
        } else {
            commentList = commentListParameter;
        }
        return imageService.persistImage(multipartFile, "originalFileName.jpg", commentList);
    }

    @Test
    public void loadRandomImageAsByteArray() throws Exception {

    }

    @Test
    public void getImageFolderLocationFromConfig() throws Exception {

    }
}