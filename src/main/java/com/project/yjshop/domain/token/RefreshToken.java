package com.project.yjshop.domain.token;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import javax.persistence.Column;
import javax.persistence.Id;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "refresh")
public class RefreshToken {

    @Id
    @Column(name = "refresh_id")
    private Integer id;

    @Indexed
    private String token;

    private String username;

    @TimeToLive
    private Integer delTime;

    public void resetTime(Integer delTime) { this.delTime = delTime; }
}
