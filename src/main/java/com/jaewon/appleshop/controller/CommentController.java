package com.jaewon.appleshop.controller;

import com.jaewon.appleshop.domain.Comment;
import com.jaewon.appleshop.domain.Item;
import com.jaewon.appleshop.domain.Member;
import com.jaewon.appleshop.service.CommentService;
import com.jaewon.appleshop.service.ItemService;
import com.jaewon.appleshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final MemberService memberService;
    private final CommentService commentService;
    private final ItemService itemService;


    @PostMapping("/comment/{itemId}")
    public String comment(@ModelAttribute("item") Comment comment, Principal principal, @PathVariable Long itemId) {
        Member member = memberService.findByUsername(principal.getName());
        Item item = itemService.findById(itemId);

        comment.setMember(member);
        comment.setItem(item);

        commentService.save(comment);
        return "redirect:/detail/" + itemId;
    }
}
