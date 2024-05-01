package com.jaewon.appleshop.repository;

import com.jaewon.appleshop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
