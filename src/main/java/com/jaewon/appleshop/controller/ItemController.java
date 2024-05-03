package com.jaewon.appleshop.controller;

import com.jaewon.appleshop.domain.Comment;
import com.jaewon.appleshop.domain.Item;
import com.jaewon.appleshop.domain.Member;
import com.jaewon.appleshop.repository.ItemRepository;
import com.jaewon.appleshop.service.CommentService;
import com.jaewon.appleshop.service.ItemService;
import com.jaewon.appleshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final ItemRepository itemRepository;
    private final CommentService commentService;

    @GetMapping("/list")
    public String list(Model model) {


        List<Item> items = itemService.findAll();

        model.addAttribute("items", items);
        return "list";
    }

    @GetMapping("/write")
    public String write() {
        return "write";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("item") Item item, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());

        item.setMember(member);
        itemService.save(item);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {

        Item item = itemService.findById(id);
        List<Comment> comments = commentService.findAllByItemId(id);  // Updated method name

        model.addAttribute("item", item);
        model.addAttribute("comments", comments);

        return "detail";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable Long id, Model model) {
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        return "modify";
    }

    @PostMapping("/modify/{id}")
    public String modify(@PathVariable Long id,Item item){
        itemService.update(id, item);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/list";
    }

    @GetMapping("/list/page/{num}")
    public String getListPage(Model model, @PathVariable int num) {
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(num-1,5));

        model.addAttribute("items", result);
        return "list";
    }


    @PostMapping("/search")
    public String search(@RequestParam String searchText, Model model) {

        List<Item> result = itemRepository.findAllByTitleContains(searchText);

        model.addAttribute("items", result);
        return "list";
    }

}