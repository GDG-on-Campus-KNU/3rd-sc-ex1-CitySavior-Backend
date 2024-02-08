package GDSCKNU.CitySavior.exception;

import GDSCKNU.CitySavior.exception.error.MemberError;
import GDSCKNU.CitySavior.global.exception.model.ExceptionBase;

public class MemberException extends ExceptionBase {

    public MemberException(MemberError memberError) {
        super(memberError, memberError.getMessage());
    }
}
