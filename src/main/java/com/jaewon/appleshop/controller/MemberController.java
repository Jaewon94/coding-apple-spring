package com.jaewon.appleshop.controller;

import com.jaewon.appleshop.domain.Member;
import com.jaewon.appleshop.service.MemberService;
import com.jaewon.appleshop.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute Member member) {
        memberService.join(member);

        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth, Model model) {
        MyUserDetailsService.CustomUser principal = (MyUserDetailsService.CustomUser) auth.getPrincipal();
        System.out.println("principal.getDisplayName() = " + principal.getDisplayName());
        return "myPage";
    }
}
