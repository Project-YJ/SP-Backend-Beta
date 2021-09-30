package com.project.yjshop.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.yjshop.domain.board.category.Category;
import com.project.yjshop.domain.image.Image;
import com.project.yjshop.domain.user.User;
import com.project.yjshop.error.ErrorCode;
import com.project.yjshop.error.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"user", "totalRevenue"})
@Entity
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    private Long count;

    private Long price;

    @OneToOne(fetch = FetchType.EAGER)
    private Image titleImage;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Image> images;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Long totalRevenue;

    @OneToOne(fetch = FetchType.LAZY)
    private Category category;

    public Board removeCount(Long count) {
        if(this.count >= count) {
            this.count -= count;
            return Board.this;
        } else {
            throw new CustomException(ErrorCode.COUNT_IS_BIG);
        }
    }

    public void revenue(Long revenue) {
        totalRevenue += revenue;
    }
}
