package com.project.yjshop.domain.board;

import com.project.yjshop.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String price;
    
    @OneToOne
    private Image titleImage;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Image> images;
}
