package com.project.yjshop.domain.board.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);

    @Query(value = "SELECT * FROM category ORDER BY count DESC", nativeQuery = true)
    List<Category> findAllDesc();
}
