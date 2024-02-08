package GDSCKNU.CitySavior.global.jwt.provider;

import GDSCKNU.CitySavior.dto.member.response.TokenResponse;
import GDSCKNU.CitySavior.global.jwt.entity.NoPasswordAuthenticationToken;
import GDSCKNU.CitySavior.global.jwt.exception.JwtException;
import GDSCKNU.CitySavior.global.jwt.exception.error.JwtError;
import GDSCKNU.CitySavior.global.redis.service.RedisService;
import GDSCKNU.CitySavior.service.member.detail.MemberDetailServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional(readOnly = true)
public class JwtTokenProvider implements InitializingBean {

    private final MemberDetailServiceImpl memberDetailService;
    private final RedisService redisService;

    private static Key signingKey;
    private final String secretKey;

    private final Long accessTokenExpiration;
    private final Long refreshTokenExpiration;

    public JwtTokenProvider(
            MemberDetailServiceImpl memberDetailService,
            RedisService redisService,
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.access-token-expiration}") Long accessTokenExpiration,
            @Value("${jwt.refresh-token-expiration}") Long refreshTokenExpiration) {
        this.secretKey = secretKey;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
        this.memberDetailService = memberDetailService;
        this.redisService = redisService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] secretKeyBytes = Decoders.BASE64.decode(secretKey);
        signingKey = Keys.hmacShaKeyFor(secretKeyBytes);
    }

    public TokenResponse createToken(String email, String authorities) {
        Long currentTime = System.currentTimeMillis();

        String accessToken = generateAccessToken(email, authorities, currentTime);
        String refreshToken = generateRefreshToken(currentTime);

        return new TokenResponse(accessToken, refreshToken);
    }

    public Claims getClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Authentication getAuthentication(String accessToken) {
        UserDetails userDetails = getUserDetailsFromToken(accessToken);
        return new NoPasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        // userName == email
    }

    private UserDetails getUserDetailsFromToken(String accessToken) {
        String email = getClaims(accessToken).get("email").toString();
        return memberDetailService.loadUserByUsername(email);
    }


    public long getTokenExpirationTime(String token) {
        return getClaims(token).getExpiration().getTime();
    }

    public void validateRefreshToken(String refreshToken) {
        try {
            if (redisService.getValues(refreshToken) == null) {
                throw new JwtException(JwtError.INVALID_JWT_TOKEN);
            }
            buildToken(refreshToken);
        } catch (SignatureException | MalformedJwtException e) {
            throw new JwtException(JwtError.INVALID_JWT_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new JwtException(JwtError.EXPIRED_JWT_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new JwtException(JwtError.UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new JwtException(JwtError.EMPTY_JWT_CLAIM_TOKEN);
        } catch (NullPointerException e) {
            throw new JwtException(JwtError.EMPTY_JWT_TOKEN);
        }
    }

    public void validateAccessToken(String accessToken) {
        try {
            if (redisService.getValues(accessToken) != null && redisService.getValues(accessToken).equals("logout")) {
                throw new JwtException(JwtError.INVALID_JWT_TOKEN);
            }
            buildToken(accessToken);
        } catch (ExpiredJwtException e) {
            throw new JwtException(JwtError.EXPIRED_JWT_TOKEN);
        } catch (Exception e) {
            throw new JwtException(JwtError.INVALID_JWT_TOKEN);
        }
    }

    private String generateAccessToken(String email, String authorities, Long currentTime) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setExpiration(new Date(currentTime * 1000L + accessTokenExpiration))
                .setSubject("access-token")
                .claim("email", email)
                .claim("role", authorities)
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .compact();
    }

    private String generateRefreshToken(Long currentTime) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setExpiration(new Date(currentTime * 1000L + refreshTokenExpiration))
                .setSubject("refresh-token")
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .compact();
    }

    private void buildToken(String token) {
        Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token);
    }
}
