package GDSCKNU.CitySavior.exception;

import GDSCKNU.CitySavior.exception.error.AIError;
import GDSCKNU.CitySavior.exception.error.ReportError;
import GDSCKNU.CitySavior.global.exception.model.ExceptionBase;

public class AIException extends ExceptionBase {

    public AIException(AIError aiError) {
        super(aiError, aiError.getMessage());
    }
}
