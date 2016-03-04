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

    /**
     * Loads UserdetailsObject if username is correct and found
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        ArrayList<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(authority);
        User user = new User("user", passwordEncoder.encode("password"), grantedAuthorities);
        User user1234 = new User("user1234", passwordEncoder.encode("password1234"), grantedAuthorities);
        if (username.equals("user")) {
            //Palautetaan peruskäyttäjä takaisin
            return user;
        }
        if (username.equals("user1234")) {
            //Palautetaan käyttäjä 1234
            return user1234;
        }
        if (username.equals("basicuser4321")) {
            return new User("basicuser4321", passwordEncoder.encode("basicuser4321"), grantedAuthorities);
        }
        return null;
    }
}
