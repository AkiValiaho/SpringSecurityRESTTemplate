package com.valiaho.Service;

import com.valiaho.Utils.TokenHandler;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by akivv on 21.2.2016.
 */
@Service
public class LoggedInUsersService {
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoggedInUsersService.class);
    @Autowired
    TokenHandler tokenHandler;
    private Map<String, Authentication> authenticationAndString = new HashMap<>();

    public void addAuthAndToken(Authentication auth, String s) {
        if (authenticationAndString.containsValue(auth)) {
            final Set<Map.Entry<String, Authentication>> entries = authenticationAndString.entrySet();
            Authentication tmp =null;
            for (Iterator<Map.Entry<String, Authentication>> iterator = entries.iterator(); iterator.hasNext(); ) {
                Map.Entry<String, Authentication> next = iterator.next();
                if (next.getValue().equals(auth)) {
                    //Take auth as tmp and add it with new token.
                    tmp = next.getValue();
                    iterator.remove();
                }
            }
            if (tmp != null) {
                authenticationAndString.put(s, tmp);
            }
        }
        authenticationAndString.put(s, auth);
    }

    public Authentication getAuthWithToken(String token) {
        if (authenticationAndString.containsKey(token)) {
            //Check if token is valid
            try {
                tokenHandler.verifyToken(token);
                return authenticationAndString.get(token);
            } catch (RuntimeException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
