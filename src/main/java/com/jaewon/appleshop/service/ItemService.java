package com.jaewon.appleshop.service;

import com.jaewon.appleshop.domain.Item;
import com.jaewon.appleshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public void save(Item item) {
        itemRepository.save(item);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }


    public void update(Long id, Item item) {
        item.setId(id);
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
