package com.valiaho.Service;

import com.valiaho.Domain.LoginStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidKeyException;

/**
 * Created by akivv on 21.2.2016.
 */
@Service
@PropertySource("classpath:tokenSecrets/token.properties")
@ComponentScan(basePackages = "com.valiaho")
public class LoginService {
    @Autowired
    Environment environment;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenAuthenticationService tokenAuthenticationService;
    @Autowired
    LoggedInUsersService loggedInUsersService;

    public LoginStatus attemptAuth(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken, HttpServletResponse response, HttpServletRequest request) throws InvalidKeyException {
        try {
            Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(auth);
            String s = tokenAuthenticationService.getTokenFromUser(response, auth);
            loggedInUsersService.addAuthAndToken(auth, s);
            return new LoginStatus(true, auth.getName(), s);
        } catch (BadCredentialsException e) {
            return new LoginStatus(false, null, null);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return new LoginStatus(false, null, null);
    }
}
