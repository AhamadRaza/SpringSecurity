package com.security.SpringSecurity.service.impl;

import com.security.SpringSecurity.model.UserInfo;
import com.security.SpringSecurity.repository.UserRepository;
import com.security.SpringSecurity.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class LoginServiceImpl implements LoginService{
    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findEmailIgnoreCase(username,true);
        if(userInfo==null){
            throw new UsernameNotFoundException("user name not found"+username);
        }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+userInfo.getRole());
        UserDetails userDetails = new User(userInfo.getEmail(), userInfo.getPassword(), Arrays.asList(grantedAuthority));
        return userDetails;
    }
}