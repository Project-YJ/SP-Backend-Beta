package com.project.yjshop.domain.user;

import javax.persistence.*;


@Entity(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;


}
