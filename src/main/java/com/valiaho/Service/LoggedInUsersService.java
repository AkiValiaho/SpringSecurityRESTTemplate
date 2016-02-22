package com.valiaho.Service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akivv on 21.2.2016.
 */
@Service
public class LoggedInUsersService {
    private Map<String, Authentication> authenticationAndString = new HashMap<>();
    public void addAuthAndToken(Authentication auth, String s) {
        authenticationAndString.put(s, auth);

    }

    public Authentication getAuthWithToken(String token) {
        return authenticationAndString.get(token);
    }
}
