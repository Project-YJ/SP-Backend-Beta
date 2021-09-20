package com.project.yjshop.domain.user.basket;

import com.project.yjshop.domain.board.Board;
import com.project.yjshop.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Long count;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Board product;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
}
