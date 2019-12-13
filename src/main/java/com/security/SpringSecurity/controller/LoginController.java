package com.security.SpringSecurity.controller;

import com.security.SpringSecurity.model.UserInfo;
import com.security.SpringSecurity.service.SecurityService;
import com.security.SpringSecurity.service.UserService;
import com.security.SpringSecurity.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired private SecurityService securityService;
    @Autowired private UserService userService;
    @Autowired private ServletContext context;
    @GetMapping(value = "/")
    public String login(){
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String loginpage(){
        return "view/login";
    }

    @GetMapping(value = "/loginfailure")
    public String loginfailure(Model model){
        String email = (String) context.getAttribute("email");
        String password = (String) context.getAttribute("password");
        System.out.println(email+"  "+password);
        model.addAttribute("errormessage", "invalid email or password");
        return "view/login";
    }

    @GetMapping(value = "/access-denied")
    public String accessdenied(Model model){
        model.addAttribute("errormessage", "Access Denied");
        return "view/login";
    }

    @GetMapping(value = "/logout")
    public String logoutsuccess(Model model){
        model.addAttribute("successmessage", "Logout Successfully");
        return "view/login";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model){
        model.addAttribute("user", new UserInfo());
        return "view/registration";
    }

    @PostMapping(value = "/registration-process")
    public String registration(@ModelAttribute UserInfo userInfo, HttpServletRequest request){
        String password = userInfo.getPassword();
        UserInfo dbuser = userService.save(userInfo);
        if(dbuser!= null){
            securityService.autoLogin(userInfo.getEmail(), password, request);
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
        return "redirect:/registration";
    }
}