package com.project.yjshop.domain.board;

import com.project.yjshop.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private Long price;

    @OneToOne
    private Image titleImage;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private List<Image> images;
}
