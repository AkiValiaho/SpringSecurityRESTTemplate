package com.valiaho.Controller;

import com.valiaho.Domain.Dto.LoginDto;
import com.valiaho.Domain.LoginStatus;
import com.valiaho.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidKeyException;

/**
 * Created by akivv on 18.2.2016.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public LoginStatus login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginDto loginDto) throws InvalidKeyException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword());
        final LoginStatus loginStatus = loginService.attemptAuth(usernamePasswordAuthenticationToken, response, request);
        return loginService.attemptAuth(usernamePasswordAuthenticationToken, response, request);
    }
}

