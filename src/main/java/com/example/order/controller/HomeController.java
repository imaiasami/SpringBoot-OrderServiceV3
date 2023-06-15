package com.example.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.order.config.PrincipalDetails;
import com.example.order.config.UserInfo;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal PrincipalDetails userInfo, Model model) {
    	model.addAttribute("loginUser", userInfo);
        return "index";
    }
    
    @GetMapping("admin")
    public String adminHome(@AuthenticationPrincipal UserInfo userInfo) {
    	return "admin/index";
    }
}
