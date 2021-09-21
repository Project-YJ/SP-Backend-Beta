package com.project.yjshop.domain.user.basket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"user"})
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="bakset_uk",
                        columnNames = {"product_id", "user_id"}
                )
        }
)
@Entity(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long count;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Board product;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
}
