package com.security.SpringSecurity.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil
{
    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String getPasswordHas(String password){
       return passwordEncoder.encode(password);
    }
}
