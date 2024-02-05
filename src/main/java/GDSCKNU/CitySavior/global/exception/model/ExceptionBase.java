package GDSCKNU.CitySavior.global.exception.model;

import GDSCKNU.CitySavior.global.exception.error.ErrorEnumBase;
import lombok.Getter;

@Getter
public class ExceptionBase extends RuntimeException {

    private final ErrorEnumBase errorEnumBase;

    public ExceptionBase(ErrorEnumBase errorEnumBase, String message) {
        super(message);
        this.errorEnumBase = errorEnumBase;
    }

    public int getHttpStatus() {
        return errorEnumBase.getHttpStatusCode();
    }

    public ErrorEnumBase getErrorEnumBase() {
        return errorEnumBase;
    }

}
