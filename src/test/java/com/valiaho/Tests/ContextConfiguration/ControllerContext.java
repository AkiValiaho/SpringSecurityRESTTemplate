package com.valiaho.Tests.ContextConfiguration;

import com.valiaho.Controller.ImageController;
import com.valiaho.Service.ImageService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by akivv on 28.4.2016.
 */
@Configuration
public class ControllerContext {
    @Bean
    ImageController imageController() {
        return new ImageController();
    }

    @Bean
    MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Bean
    ImageService imageService() {
        System.out.println("Hello world");
        return Mockito.mock(ImageService.class);
    }
}
