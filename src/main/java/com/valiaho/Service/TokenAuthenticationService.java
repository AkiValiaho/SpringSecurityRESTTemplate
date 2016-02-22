package com.valiaho.Service;

import com.auth0.jwt.JWTVerifyException;
import com.valiaho.Utils.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

/**
 * Created by akivv on 20.2.2016.
 */
@Service
@PropertySource("classpath:tokenSecrets/token.properties")
public class TokenAuthenticationService {
    private static final String AUTH_HEADER_NAME = "X-Auth-Header";
    private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

    @Autowired
    private final TokenHandler tokenHandler;

    @Autowired
    private LoggedInUsersService loggedInUsersService;

    @Autowired
    private LoginService loginService;

    public TokenAuthenticationService() {
        tokenHandler = new TokenHandler();
    }

    public String getTokenFromUser(HttpServletResponse response, Authentication authentication) throws InvalidKeyException {
        final User user = (User) authentication.getPrincipal();
        try {
            return tokenHandler.createTokenForUser(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Authentication getUserFromToken(HttpServletRequest request) throws NoSuchAlgorithmException, SignatureException, JWTVerifyException, InvalidKeyException, IOException {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final Authentication user = loggedInUsersService.getAuthWithToken(token);
            if (user != null) {
                return user;
            }
        }
        return null;
    }
}
