package com.valiaho.Tests;

import com.valiaho.Configuration.Web.CustomuserDetailService;
import com.valiaho.DAO.ImageRepository;
import com.valiaho.Domain.Image;
import com.valiaho.Service.ImageService;
import com.valiaho.Service.ImageServiceImpl;
import com.valiaho.Service.LoggedInUsersService;
import com.valiaho.Utils.MultiPartFileUtils;
import com.valiaho.Utils.TokenHandler;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by akivv on 23.2.2016.
 */
@Configuration
public class MyTestContext {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    TokenHandler tokenHandler() {
        return new TokenHandler();
    }

    @Bean
    CustomuserDetailService customuserDetailService() {
        return new CustomuserDetailService();
    }

    @Bean
    Environment environment() {
        MockEnvironment mockEnvironment = new MockEnvironment();
        mockEnvironment.setProperty("tokenDefaultLoginTime", "864000000");
        mockEnvironment.setProperty("imageFileFolderLocation", "/home/akivv/images");
        return mockEnvironment;
    }

    @Bean
    ImageService imageService() {
        return new ImageServiceImpl();
    }

    @Bean
    LoggedInUsersService loggedInUsersService() {
        return new LoggedInUsersService();
    }

    @Bean
    ImageRepository imageRepository() {
        final ImageRepository mock = Mockito.mock(ImageRepository.class);
        when(mock.save(any())).thenReturn(Mockito.mock(Image.class));
        return mock;
    }

    @Bean
    MultiPartFileUtils multiPartFileUtils() {
        return new MultiPartFileUtils();
    }
}
