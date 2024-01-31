package GDSCKNU.CitySavior.exception.success;

import GDSCKNU.CitySavior.global.exception.error.ErrorEnumBase;
import GDSCKNU.CitySavior.global.exception.error.SuccessEnumBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AuthSuccessEnum implements SuccessEnumBase {

    JOIN_APPLICATION_AND_MAKE_TOKEN_SUCCESS(HttpStatus.OK, "회원가입과 Token 만들기에 성공했습니다."),
    LOGIN_APPLICATION_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    REISSUE_APPLICATION_SUCCESS(HttpStatus.OK, "토큰 재발급에 성공했습니다."),
    LOGOUT_APPLICATION_SUCCESS(HttpStatus.OK, "로그아웃에 성공했습니다"),
    ;

    final HttpStatus httpStatus;
    final String message;

    @Override
    public ErrorEnumBase[] getErrors() {
        return new ErrorEnumBase[0];
    }

    @Override
    public int getHttpStatusCode() {
        return httpStatus.value();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
