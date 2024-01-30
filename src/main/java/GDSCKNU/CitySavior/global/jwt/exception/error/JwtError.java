package GDSCKNU.CitySavior.global.jwt.exception.error;

import GDSCKNU.CitySavior.global.exception.error.ErrorEnumBase;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum JwtError implements ErrorEnumBase {

    INVALID_JWT_SIGNATURE(HttpStatus.NOT_ACCEPTABLE, "유효하지 않은 JWT signature 입니다."),
    INVALID_JWT_TOKEN(HttpStatus.NOT_ACCEPTABLE, "유효하지 않은 Token 입니다,"),
    EXPIRED_JWT_TOKEN(HttpStatus.NOT_ACCEPTABLE, "만료된 Token 입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.NOT_ACCEPTABLE, "지원하지 않는 Token 입니다."),
    EMPTY_JWT_CLAIM_TOKEN(HttpStatus.NOT_ACCEPTABLE, "Jwt Claim이 비어있습니다."),
    EMPTY_JWT_TOKEN(HttpStatus.NOT_ACCEPTABLE, "Jwt Token이 비어있습니다.");

    final HttpStatus httpStatus;
    final String message;

    @Override
    public ErrorEnumBase[] getErrors() {
        return new ErrorEnumBase[0];
    }

    @Override
    public int getHttpStatusCode() {
        return this.httpStatus.value();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
