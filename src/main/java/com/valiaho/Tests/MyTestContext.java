package com.valiaho.Tests;

import com.valiaho.Configuration.Web.CustomuserDetailService;
import com.valiaho.Service.LoggedInUsersService;
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
        return mockEnvironment;
    }
    @Bean
    LoggedInUsersService loggedInUsersService() {
        return  new LoggedInUsersService();
    }
}
