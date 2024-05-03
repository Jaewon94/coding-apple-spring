package com.jaewon.appleshop.service;

import com.jaewon.appleshop.domain.Comment;
import com.jaewon.appleshop.repository.CommentRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRespository commentRespository;


    public void save(Comment comment) {
        commentRespository.save(comment);
    }

    public List<Comment> findAllByItemId(Long id) {
        return commentRespository.findAllByItemId(id);
    }
}
