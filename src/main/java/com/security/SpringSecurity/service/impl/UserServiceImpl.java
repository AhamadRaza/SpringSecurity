package com.security.SpringSecurity.service.impl;

import com.security.SpringSecurity.model.UserInfo;
import com.security.SpringSecurity.repository.UserRepository;
import com.security.SpringSecurity.service.UserService;
import com.security.SpringSecurity.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired private UserRepository userRepository;

    @Override
    public UserInfo findByEmail(String email) {
        return userRepository.findEmailIgnoreCase(email, true);
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        String password = PasswordUtil.getPasswordHas(userInfo.getPassword());
        userInfo.setPassword(password);
        userInfo.setEnabled(true);
        userInfo.setCreatedDate(new Date());
        return userRepository.save(userInfo);
    }
}