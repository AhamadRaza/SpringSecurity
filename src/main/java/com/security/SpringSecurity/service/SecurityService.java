package com.security.SpringSecurity.service;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    public void autoLogin(String email, String password, HttpServletRequest request);
}