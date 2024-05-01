package com.jaewon.appleshop.controller;

import com.jaewon.appleshop.domain.Item;
import com.jaewon.appleshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/list")
    public String list(Model model) {

        List<Item> items = itemRepository.findAll();

        model.addAttribute("items", items);
        return "list";
    }
}
