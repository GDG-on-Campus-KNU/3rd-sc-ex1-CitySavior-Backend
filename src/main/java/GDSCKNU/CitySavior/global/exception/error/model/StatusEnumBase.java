package GDSCKNU.CitySavior.global.exception.error.model;

import GDSCKNU.CitySavior.global.exception.error.ErrorEnumBase;

public interface StatusEnumBase {

    ErrorEnumBase[] getErrors();

    int getHttpStatusCode();

    String getMessage();
}
