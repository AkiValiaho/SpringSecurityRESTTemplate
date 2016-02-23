package com.valiaho.Configuration.SecurityConfig;

import com.valiaho.Configuration.Web.CustomuserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

/**
 * Created by akivv on 7.2.2016.
 */
@Configuration
public class DevSecurityConfig extends BaseSecurityConfig {

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomuserDetailService();
    }

    @Override
    @Bean
    public PasswordEncoder passwordEncoder() {
        SecureRandom random = new SecureRandom();
        //Use SecureRandom to generate a cryptographically
        //strong key
        return new BCryptPasswordEncoder(10, random);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
