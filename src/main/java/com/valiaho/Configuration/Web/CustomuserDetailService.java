package com.valiaho.Configuration.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by akivv on 18.2.2016.
 */
@Component
public class CustomuserDetailService implements UserDetailsService {
     @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        ArrayList<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(authority);
        User user = new User("user", passwordEncoder.encode("password"), grantedAuthorities);
        if (username.equals("user")) {
            return user;
        }
        return null;
    }
}
