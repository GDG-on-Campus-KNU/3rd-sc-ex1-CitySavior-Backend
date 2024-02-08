package GDSCKNU.CitySavior.global.jwt.exception;

import GDSCKNU.CitySavior.global.exception.model.ExceptionBase;
import GDSCKNU.CitySavior.global.jwt.exception.error.JwtError;

public class JwtException extends ExceptionBase {

    public JwtException(JwtError jwtError) {
        super(jwtError, jwtError.getMessage());
    }
}
