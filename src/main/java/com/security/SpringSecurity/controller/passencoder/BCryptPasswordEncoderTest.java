package com.security.SpringSecurity.controller.passencoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest
{
    public static void main(String[] args)
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("1234567"));
     //   System.out.println(bCryptPasswordEncoder.encode("r2@123"));
    }

}
