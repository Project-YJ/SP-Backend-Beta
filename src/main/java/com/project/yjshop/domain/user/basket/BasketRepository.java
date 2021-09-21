package com.project.yjshop.domain.user.basket;

import com.project.yjshop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findAllByUser(User user);
}
