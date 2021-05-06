package com.gonchcharov.ru.EPAMFinalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
    @GetMapping("/")
    public String showMyLoginPage(){
        return "fancy-login";
    }


    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";

    }
}