package com.valiaho.Tests;

import com.valiaho.Tests.ContextConfiguration.GenericTestContext;
import com.valiaho.Utils.TokenHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akivv on 22.3.2016.
 */
@ContextConfiguration(classes = {GenericTestContext.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TokenHandlerTest {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenHandler tokenHandler;
    private User user;

    @Before
    public void createCOntext() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        user = new User("Aki Väliaho", passwordEncoder.encode("asdf"), grantedAuthorities);
    }

    @Test
    public void testCreateTokenForUser() throws Exception {
        tokenHandler.verifyToken(tokenHandler.createTokenForUser(user));
    }
}