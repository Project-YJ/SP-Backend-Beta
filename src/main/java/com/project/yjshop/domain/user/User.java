package com.project.yjshop.domain.user;

import com.project.yjshop.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    private Long money;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    public void plusMoney(Long money) {
        this.money += money;
    }
    public Long removeMoney(Long money) {
        return this.money -= money;
    }
}
