package GDSCKNU.CitySavior.exception.error;

import GDSCKNU.CitySavior.global.exception.error.ErrorEnumBase;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MemberError implements ErrorEnumBase {

    MEMBER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "존제하는 회원을 찾지 못했습니다."),
    EXIST_MEMBER_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 회원 입니다.");

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
