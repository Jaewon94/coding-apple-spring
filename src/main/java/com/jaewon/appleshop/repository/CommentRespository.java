package com.jaewon.appleshop.repository;

import com.jaewon.appleshop.domain.Comment;
import com.jaewon.appleshop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRespository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByItemId(Long id);
}
