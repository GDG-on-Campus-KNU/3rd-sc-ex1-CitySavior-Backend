package GDSCKNU.CitySavior.service.auth;

import GDSCKNU.CitySavior.dto.member.request.MemberCreateV1Request;
import GDSCKNU.CitySavior.dto.member.request.MemberLoginV1Request;
import GDSCKNU.CitySavior.dto.member.response.TokenResponse;
import GDSCKNU.CitySavior.entity.member.Member;
import GDSCKNU.CitySavior.entity.member.MemberRole;
import GDSCKNU.CitySavior.exception.MemberException;
import GDSCKNU.CitySavior.exception.error.MemberError;
import GDSCKNU.CitySavior.global.jwt.entity.NoPasswordAuthenticationToken;
import GDSCKNU.CitySavior.global.jwt.provider.JwtTokenProvider;
import GDSCKNU.CitySavior.global.redis.service.RedisService;
import GDSCKNU.CitySavior.repository.MemberRepository;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RedisService redisService;

    private final static String SERVER = "server";
    private final static List<GrantedAuthority> DEFAULT_ROLE = Collections.singletonList(
            new SimpleGrantedAuthority(MemberRole.USER.name()));

    @Transactional
    public TokenResponse join(MemberCreateV1Request memberCreateV1Request) {
        Authentication authentication = getAuthentication(memberCreateV1Request.email());

        return generateToken(SERVER, memberCreateV1Request.email(), getAuthorities(authentication));
    }

    @Transactional
    public TokenResponse login(MemberLoginV1Request loginDto) {
        Authentication authentication = getAuthentication(loginDto.email());

        return generateToken(SERVER, loginDto.email(), getAuthorities(authentication));
    }

    // 토큰 재 발급
    @Transactional
    public TokenResponse reissue(String accessToken, String refreshToken) {

        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        String principal = getPrincipal(accessToken);

        String refreshTokenInRedis = redisService.getValues("RT(" + SERVER + "):" + principal);
        if (refreshTokenInRedis == null) {
            return null;
        }

        if (!refreshTokenInRedis.equals(refreshToken)) {
            jwtTokenProvider.validateRefreshToken(refreshToken);
            redisService.deleteValues("RT(" + SERVER + "):" + principal);
            return null;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String authorities = getAuthorities(authentication);

        redisService.deleteValues("RT(" + SERVER + "):" + principal);
        TokenResponse tokenDto = jwtTokenProvider.createToken(principal, authorities);
        saveRefreshToken(SERVER, principal, tokenDto.refreshToken());
        return tokenDto;
    }

    // 토큰 발급
    @Transactional
    public TokenResponse generateToken(String provider, String email, String authorities) {
        if (redisService.getValues("RT(" + provider + "):" + email) != null) {
            redisService.deleteValues("RT(" + provider + "):" + email); // 삭제
        }

        TokenResponse tokenDto = jwtTokenProvider.createToken(email, authorities);
        saveRefreshToken(provider, email, tokenDto.refreshToken());
        return tokenDto;
    }

    @Transactional
    public void saveRefreshToken(String provider, String principal, String refreshToken) {
        redisService.setValuesWithTimeout("RT(" + provider + "):" + principal, // key
                refreshToken, // value
                jwtTokenProvider.getTokenExpirationTime(refreshToken)); // timeout(milliseconds)
    }

    // 로그아웃
    @Transactional
    public void logout(String requestAccessTokenInHeader) {
        String requestAccessToken = resolveToken(requestAccessTokenInHeader);
        String principal = getPrincipal(requestAccessToken);
        // Redis에 저장되어 있는 RT 삭제
        String refreshTokenInRedis = redisService.getValues("RT(" + SERVER + "):" + principal);
        if (refreshTokenInRedis != null) {
            redisService.deleteValues("RT(" + SERVER + "):" + principal);
        }

        // Redis에 로그아웃 처리한 AT 저장
        long expiration = jwtTokenProvider.getTokenExpirationTime(requestAccessToken) - new Date().getTime();
        redisService.setValuesWithTimeout(requestAccessToken,
                "logout",
                expiration);
    }

    /*
    public String getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }*/

    public String getAuthorities(Authentication authentication) {
        Member member = memberRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new MemberException(MemberError.MEMBER_NOT_FOUND_ERROR));

        return member.isAdminMember();
    }

    public String getPrincipal(String requestAccessToken) {
        return jwtTokenProvider.getAuthentication(requestAccessToken).getName();
    }

    public String resolveToken(String requestAccessTokenInHeader) {
        if (requestAccessTokenInHeader != null && requestAccessTokenInHeader.startsWith("Bearer ")) {
            return requestAccessTokenInHeader.substring(7);
        }
        return null;
    }

    private Authentication getAuthentication(String email) {
        try {
            NoPasswordAuthenticationToken authenticationToken =
                    new NoPasswordAuthenticationToken(email, null, DEFAULT_ROLE); // 원래는 null에 비밀번호
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return authentication;
        } catch (AuthenticationException e) {
            throw e;
        }
    }

}
