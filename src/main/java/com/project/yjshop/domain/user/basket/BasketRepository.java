package com.project.yjshop.domain.user.basket;

import com.project.yjshop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
    List<Basket> findAllByUser(User user);

    @Query(value = "SELECT * FROM basket WHERE product_id = :product_id AND user_id = :user_id", nativeQuery = true)
    Optional<Basket> mBasket(Integer product_id, Integer user_id);
}
