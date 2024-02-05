package GDSCKNU.CitySavior.global.exception.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GlobalError implements ErrorEnumBase {

    REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "요청에 Null Pointer Exception이 발생했습니다."),
    INTERNAL_DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 데이터베이스 에러가 발생했습니다."),
    ;

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
