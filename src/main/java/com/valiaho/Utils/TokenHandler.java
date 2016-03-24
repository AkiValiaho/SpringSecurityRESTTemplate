package com.valiaho.Utils;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by akivv on 20.2.2016.
 */
@Service
@PropertySource("classpath:tokenSecrets/token.properties")
public class TokenHandler {
    @Autowired
    private Environment environment;
    private String secret;
    private String tokenDefaultLoginTime;

    public TokenHandler() {
        this.secret = "asdf";
    }

    @PostConstruct
    public void init() {
//        this.secret = environment.getRequiredProperty("tokenSecret");
        this.tokenDefaultLoginTime = environment.getRequiredProperty("tokenDefaultLoginTime");
    }

    public String createTokenForUser(User user) throws NoSuchAlgorithmException, InvalidKeyException {
        final Map<String, Object> mapOfPayloads = new HashMap<>();
        mapOfPayloads.put("exp", Integer.valueOf(tokenDefaultLoginTime) + System.currentTimeMillis());
        mapOfPayloads.put("role", user.getAuthorities());
        if (mapOfPayloads.get("role") == null) {
            mapOfPayloads.put("role", "ANONYMOUS");
        }
        Gson gson = new Gson();
        final String s = gson.toJson(mapOfPayloads);
        final String compact = Jwts.builder().setPayload(s).signWith(SignatureAlgorithm.HS256, this.secret).compact();
        return compact;
    }

    public void verifyToken(String token) throws RuntimeException {
        final Jwt parse = Jwts.parser().setSigningKey(this.secret).parse(token);
        final Long exp = (Long) ((DefaultClaims) parse.getBody()).get("exp");
        if (exp < System.currentTimeMillis()) {
            throw new RuntimeException("Access token has expired");
        }
    }
}
