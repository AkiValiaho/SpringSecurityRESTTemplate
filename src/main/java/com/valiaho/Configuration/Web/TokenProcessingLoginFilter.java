package com.valiaho.Configuration.Web;

import com.auth0.jwt.JWTVerifyException;
import com.valiaho.Service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

/**
 * Created by akivv on 20.2.2016.
 */
public class TokenProcessingLoginFilter implements Filter {
    @Autowired
    TokenAuthenticationService tokenAuthenticationService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            final Authentication userFromToken = tokenAuthenticationService.getUserFromToken((HttpServletRequest) request);
            if (userFromToken != null) {
                SecurityContextHolder.getContext().setAuthentication(userFromToken);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (JWTVerifyException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        chain.doFilter(request, response); // always continue
    }

    @Override
    public void destroy() {

    }
}
