package com.project.yjshop.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    private Integer money;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    public void plusMoney(Integer money) {
        this.money += money;
    }
    public Integer removeMoney(Integer money) {
        return this.money -= money;
    }
}
