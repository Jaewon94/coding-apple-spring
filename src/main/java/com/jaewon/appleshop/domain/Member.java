package com.jaewon.appleshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String displayName;

    @OneToMany(mappedBy = "member")
    private List<Item> items;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;
}
