package com.example.luv.service;

import com.example.luv.model.TblUser;
import com.example.luv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Giả định có một UserRepository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TblUser user = userRepository.findByLoginName(username).get();

        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getLoginName(), user.getPassword(), authorities);
    }
}
