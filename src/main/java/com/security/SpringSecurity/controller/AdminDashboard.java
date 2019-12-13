package com.security.SpringSecurity.controller;

import com.security.SpringSecurity.model.UserInfo;
import com.security.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "/admin")
public class AdminDashboard {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/dashboard")
    public String adminDashbord(Model model, Principal principal){
        UserInfo userInfo = userService.findByEmail(principal.getName());
        model.addAttribute("user", userInfo);
        return "view/admindashboard";
    }
}