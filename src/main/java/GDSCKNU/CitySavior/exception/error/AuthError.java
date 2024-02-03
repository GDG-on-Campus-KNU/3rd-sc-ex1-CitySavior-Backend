package GDSCKNU.CitySavior.exception.error;

import GDSCKNU.CitySavior.global.exception.error.ErrorEnumBase;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthError implements ErrorEnumBase {

    TOKEN_NOT_FOUND_ERROR(HttpStatus.UNAUTHORIZED, "주어진 토큰이 Null 이거나 존재하지 않습니다."),

    TOKEN_REISSUE_FAILED_ERROR(HttpStatus.UNAUTHORIZED, "reissue에 실패했습니다. Token이 null 입니다.");

    final HttpStatus status;
    final String message;

    @Override
    public ErrorEnumBase[] getErrors() {
        return new ErrorEnumBase[0];
    }

    @Override
    public int getHttpStatusCode() {
        return this.status.value();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
