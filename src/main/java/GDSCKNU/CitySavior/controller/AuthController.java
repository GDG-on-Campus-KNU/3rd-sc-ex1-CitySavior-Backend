package GDSCKNU.CitySavior.controller;

import GDSCKNU.CitySavior.dto.member.request.MemberCreateV1Request;
import GDSCKNU.CitySavior.dto.member.request.MemberLoginV1Request;
import GDSCKNU.CitySavior.dto.member.response.TokenResponse;
import GDSCKNU.CitySavior.exception.error.AuthError;
import GDSCKNU.CitySavior.exception.success.AuthSuccessEnum;
import GDSCKNU.CitySavior.global.response.model.ApiResponse;
import GDSCKNU.CitySavior.global.util.ResponseAuth;
import GDSCKNU.CitySavior.service.auth.AuthService;
import GDSCKNU.CitySavior.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Auth API")
public class AuthController {

    private final MemberService memberService;
    private final AuthService authService;
    private final static long COOKIE_EXPIRATION = 777600; // 9일

    // todo: 전체 API 만든 후 Swagger 세부 사항 추가 하기
    @Operation(
            summary = "초기 회원가입 진행"
    )
    @PostMapping("/signup")
    @Transactional
    public ApiResponse<TokenResponse> signup(@RequestBody MemberCreateV1Request memberCreateV1Request) {
        memberService.registerMember(memberCreateV1Request);
        TokenResponse tokenDto = authService.join(memberCreateV1Request);

        return ApiResponse.success(AuthSuccessEnum.JOIN_APPLICATION_AND_MAKE_TOKEN_SUCCESS, tokenDto);
    }


    @Operation(
            summary = "로그인 진행"
    )
    @PostMapping("/login")

    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody MemberLoginV1Request memberLoginV1Request) {
        TokenResponse tokenDto = authService.login(memberLoginV1Request);
        ResponseCookie responseCookie = setResponseCookie(tokenDto);

        return ResponseAuth.success(HttpStatus.OK, responseCookie, tokenDto.accessToken(),
                ApiResponse.success(AuthSuccessEnum.LOGIN_APPLICATION_SUCCESS, tokenDto));
    }


    @Operation(
            summary = "reissue가 필요한지 확인"
    )
    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestHeader("Authorization") String requestAccessTokenInHeader) {
        if (!authService.validate(requestAccessTokenInHeader)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // reissue 불 필요
        }
    }


    @Operation(
            summary = "토큰 reissue 진행"
    )
    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse<String>> reissue(@CookieValue(name = "refresh-token") String requestRefreshToken,
                                                       @RequestHeader("Authorization") String requestAccessTokenInHeader) {
        TokenResponse reissuedTokenDto = authService.reissue(requestAccessTokenInHeader, requestRefreshToken);

        if (reissuedTokenDto != null) {
            ResponseCookie responseCookie = setResponseCookie(reissuedTokenDto);

            return ResponseAuth.success(HttpStatus.OK, responseCookie, reissuedTokenDto.accessToken(),
                    ApiResponse.success(AuthSuccessEnum.REISSUE_APPLICATION_SUCCESS, reissuedTokenDto.accessToken()));

        } else {
            return ResponseAuth.logout(HttpStatus.UNAUTHORIZED, ApiResponse.error(AuthError.TOKEN_NOT_FOUND_ERROR, ""));
        }
    }


    @Operation(
            summary = "로그아웃 진행"
    )
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader("Authorization") String requestAccessTokenInHeader) {
        authService.logout(requestAccessTokenInHeader);

        return ResponseAuth.logout(HttpStatus.OK, ApiResponse.success(AuthSuccessEnum.LOGOUT_APPLICATION_SUCCESS));
    }


    private ResponseCookie setResponseCookie(TokenResponse tokenResponse) {
        return ResponseCookie.from("refresh-token", tokenResponse.refreshToken())
                .maxAge(COOKIE_EXPIRATION)
                .httpOnly(true)
                .secure(true)
                .build();
    }
}
