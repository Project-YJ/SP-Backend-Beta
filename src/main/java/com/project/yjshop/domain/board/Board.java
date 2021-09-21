package com.project.yjshop.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.yjshop.domain.image.Image;
import com.project.yjshop.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"user", "totalRevenue"})
@Entity
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

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

    public Board removeCount(Long count) {
        this.count -= count;
        return Board.this;
    }

    public void revenue(Long revenue) {
        totalRevenue += revenue;
    }
}
