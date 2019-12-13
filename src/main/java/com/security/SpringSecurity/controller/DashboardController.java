package com.security.SpringSecurity.controller;

import com.security.SpringSecurity.model.UserInfo;
import com.security.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DashboardController {
    @Autowired private UserService userService;

    @GetMapping(value = "/redirectdashboard")
    public String redirectDashboard(Principal principal){
        UserInfo userInfo = userService.findByEmail(principal.getName());
        if(userInfo!=null && StringUtils.hasText(userInfo.getRole())){
            if(userInfo.getRole().equalsIgnoreCase("USER")){
                return "redirect:/users/dashboard";
            }
            if(userInfo.getRole().equalsIgnoreCase("ADMIN")){
                return "redirect:/admin/dashboard";
            }
            if(userInfo.getRole().equalsIgnoreCase("SUPERADMIN")){
                return "redirect:/superadmin/dashboard";
            }
        }
        return "redirect:/login";
    }
}