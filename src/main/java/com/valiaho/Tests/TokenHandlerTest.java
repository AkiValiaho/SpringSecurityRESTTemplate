package com.valiaho.Tests;

import com.valiaho.Configuration.Web.CustomuserDetailService;
import com.valiaho.Utils.TokenHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by akivv on 23.2.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyTestContext.class)
public class TokenHandlerTest {

    @Autowired
    TokenHandler tokenHandler;
    @Autowired
    CustomuserDetailService customuserDetailService;
    private String token;

    @Before
    public void setUp() throws Exception {
        final UserDetails details = customuserDetailService.loadUserByUsername("user");
        token = tokenHandler.createTokenForUser((User) details);
    }

    @Test
    public void testVerifyToken() throws Exception {
        tokenHandler.verifyToken(token);
    }
}
