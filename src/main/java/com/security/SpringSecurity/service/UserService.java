package com.security.SpringSecurity.service;

import com.security.SpringSecurity.model.UserInfo;

public interface UserService {
   public UserInfo findByEmail(String email);
   public UserInfo save(UserInfo userInfo);
}