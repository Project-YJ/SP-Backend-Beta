package com.project.yjshop.domain.board.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "category_name")
    @Column(unique = true)
    private String name;

    @JoinColumn(name = "category_count")
    private Integer count;

    public Category upCount() {
        this.count++;
        return this;
    }
}
