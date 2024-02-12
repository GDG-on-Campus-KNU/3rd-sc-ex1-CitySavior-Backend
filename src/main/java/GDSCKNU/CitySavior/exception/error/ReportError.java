package GDSCKNU.CitySavior.exception.error;

import GDSCKNU.CitySavior.global.exception.error.ErrorEnumBase;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ReportError implements ErrorEnumBase {

    REPORT_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "존재하는 신고을 찾지 못했습니다."),
    AI_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "AI 서버와 통신 중 오류가 발생했습니다.");

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
