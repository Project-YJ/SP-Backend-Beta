package com.project.yjshop.domain.token;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "refresh", timeToLive = 1000)
public class RefreshToken {

    @Id
    private Long id;

    @Indexed
    private String token;
    private String username;

    @TimeToLive
    private Long refreshExp;

    public void update(Long refreshExp) {
        this.refreshExp = refreshExp;
    }
}
