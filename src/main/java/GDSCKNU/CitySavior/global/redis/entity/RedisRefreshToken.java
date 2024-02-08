package GDSCKNU.CitySavior.global.redis.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = 1209600) // 일주일 설정
public class RedisRefreshToken {

    @Id
    private String email;

    @Indexed
    private String refreshToken;

    @Builder
    public RedisRefreshToken(String email, String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }
}
