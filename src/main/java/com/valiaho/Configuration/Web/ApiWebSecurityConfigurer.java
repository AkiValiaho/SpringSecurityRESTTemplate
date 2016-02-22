package com.valiaho.Configuration.Web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by akivv on 17.2.2016.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.valiaho.Configuration")
@Order(1)
public class ApiWebSecurityConfigurer extends BaseWebSecurityConfigurationAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //ChannelProcessingFilter is the first filter
                //Add corsfilter before it
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
                .addFilterBefore(corsFilter(), ChannelProcessingFilter.class)
                .addFilterBefore(tokenProcessingLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/quizapp").hasRole("USER")
                .anyRequest().authenticated().and()
                .csrf().disable();
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }

    @Bean
    public TokenProcessingLoginFilter tokenProcessingLoginFilter() {
        return new TokenProcessingLoginFilter();
    }
}
