package com.valiaho.Tests.Controllers.ImageController;

import Utils.MockMultiPartFileWrapperUtils;
import Utils.TestingResourceGenerator;
import com.valiaho.Controller.ImageController;
import com.valiaho.Domain.Image;
import com.valiaho.Domain.MultipartFileInformationWrapper;
import com.valiaho.Service.ImageService;
import com.valiaho.Tests.ContextConfiguration.ControllerContext;
import com.valiaho.Utils.UtilityMethods;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static junit.framework.TestCase.fail;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by akivv on 28.4.2016.
 */

@ContextConfiguration(classes = {ControllerContext.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ImageControllerTest {
    @Autowired
    ImageController imageController;
    @Autowired
    ImageService imageService;
    private Logger logger = LoggerFactory.getLogger(ImageControllerTest.class);
    private MockMvc mockMvc;

    @Before
    public void initialize() {
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();

    }

    @Test
    public void testIfExceptionIsThrownWhenFalseIdGiven() {
        try {
            when(imageService.getImageById("18")).thenReturn(Optional.empty());
            final ResultActions perform = mockMvc.perform(get("/api/image/findImage/{id}", "18"));
            perform.andExpect(status().isNotFound());
            verify(imageService, times(1)).getImageById(any());
        } catch (Exception e) {
            fail("mockmvc raised an exception");
            e.printStackTrace();
        }
    }

    @Test
    public void testIfProperImageIsReturnedInProperJsonFromController() {
        final Image image = UtilityMethods.buildRandomImageAsRaster();
        when(imageService.getImageById(any())).thenReturn(Optional.of(image));
        try {
            final ResultActions perform = mockMvc.perform(get("/api/image/findImage/{id}", "15"));
            perform.andExpect(status().isOk());
            // Check if it is really JSON
            checkThatImageInfoIsCorrectInReturnedJSON(perform);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void postImageToDatabase() {
        Image image = UtilityMethods.buildRandomImageAsRaster();
        when(imageService.persistImage(any(), any(), any())).thenReturn(Optional.of(image));
        try {
            final MultipartFileInformationWrapper multipartFileInformationWrapper = MockMultiPartFileWrapperUtils.generateTestMockMultipartFileWrapper(image);
            final MockMultipartFile mockMultipartFile = TestingResourceGenerator.generateTestingMultiPartMockFile();
            final ResultActions perform = mockMvc.perform(fileUpload("/api/image/postNewImage")
                    .file(mockMultipartFile)
                    .param("originalFilename", multipartFileInformationWrapper.getOriginalFilename())
                    .param("commentList", multipartFileInformationWrapper.getCommentList().toString()));
            checkThatImageInfoIsCorrectInReturnedJSON(perform);
            System.out.println(perform);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkThatImageInfoIsCorrectInReturnedJSON(ResultActions perform) throws Exception {
        perform.andExpect(status().isOk());
        perform.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
        perform.andExpect(jsonPath("$.documentId", is("tmpImage.jpg")));
        perform.andExpect(jsonPath("$.imageLocation", is("/home/akivv/images.png")));
    }
}
