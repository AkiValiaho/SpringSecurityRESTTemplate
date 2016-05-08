package com.valiaho.Tests.ContextConfiguration;

import com.valiaho.Configuration.Web.CustomuserDetailService;
import com.valiaho.Service.LoggedInUsersService;
import com.valiaho.Utils.MultiPartFileUtils;
import com.valiaho.Utils.TokenHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by akivv on 23.2.2016.
 */
@Configuration
public class GenericTestContext {
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
        setMockEnvironmentProperties(mockEnvironment);
        return mockEnvironment;
    }

    private void setMockEnvironmentProperties(MockEnvironment mockEnvironment) {
        mockEnvironment.setProperty("tokenDefaultLoginTime", "864000000");
        mockEnvironment.setProperty("imageFileFolderLocation", "/home/akivv/images");
        dbProperties(mockEnvironment);
    }

    private void dbProperties(MockEnvironment mockEnvironment) {
        mockEnvironment.setProperty("db.connectTimeout", "6000");
        mockEnvironment.setProperty("db.bucketPassword", "ekosypin8nn23a!");
        mockEnvironment.setProperty("db.bucketName", "imageservicebucket");
    }


    @Bean
    LoggedInUsersService loggedInUsersService() {
        return new LoggedInUsersService();
    }

    @Bean
    MultiPartFileUtils multiPartFileUtils() {
        return new MultiPartFileUtils();
    }
}
