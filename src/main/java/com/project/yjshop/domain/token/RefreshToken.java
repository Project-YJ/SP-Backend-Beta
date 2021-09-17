package com.project.yjshop.domain.token;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "refresh")
public class RefreshToken {

    @Id
    @Column(name = "refresh_id")
    private Long id;

    @Indexed
    private String token;
    private String username;

//    @TimeToLive
//    private Long refreshExp;
//
//    public void update(Long refreshExp) {
//        this.refreshExp = refreshExp;
//    }
}
