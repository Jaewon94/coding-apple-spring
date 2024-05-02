package com.jaewon.appleshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {

    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/about")
    @ResponseBody
    public String about() {
        return "Hello about";
    }

    @GetMapping("/mypage")
    @ResponseBody
    public String mypage() {
        return "마이페이지 입니다.";
    }
}
